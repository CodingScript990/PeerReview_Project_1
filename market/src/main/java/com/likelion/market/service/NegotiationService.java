package com.likelion.market.service;

import com.likelion.market.dto.NegotiationDto;
import com.likelion.market.entity.ItemEntity;
import com.likelion.market.entity.NegotiationEntity;
import com.likelion.market.repository.ItemRepository;
import com.likelion.market.repository.NegotiationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NegotiationService {
    // Call Repository
    private final ItemRepository itemRepository;
    private final NegotiationRepository negotiationRepository;

    // POST /items/{itemId}/proposals
    public NegotiationDto createNegotiation(Long itemId ,NegotiationDto negotiationDto) {

        if (!itemRepository.existsById(itemId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        NegotiationEntity entity = new NegotiationEntity();

        entity.setItemId(itemId);
        entity.setWriter(negotiationDto.getWriter());
        entity.setPassword(negotiationDto.getPassword());
        entity.setSuggestedPrice(negotiationDto.getSuggestedPrice());
        entity.setStatus("제안");

        return NegotiationDto.fromEntity(negotiationRepository.save(entity));
    }

    // GET /items/{itemId}/proposals?writer=jeeho.edu&password=qwerty1234&page=0
    public Page<NegotiationDto> readNegotiation(Long itemId, String writer, String password, Integer page) {
        Optional<ItemEntity> optionalItem = itemRepository.findById(itemId);
        List<NegotiationEntity> negotiationEntityList = negotiationRepository.findByItemId(itemId);

        //대상 물품이 없을 때
        if (optionalItem.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        //물품 등록자의 경우
        ItemEntity item = optionalItem.get();
        if(item.getWriter().equals(writer) && item.getPassword().equals(password)) {
            Pageable pageable = PageRequest.of(page, 25, Sort.by("id"));
            Page<NegotiationEntity> negotiationEntityPage = negotiationRepository.findAllByItemId(itemId, pageable);
            return negotiationEntityPage.map(NegotiationDto::fromEntity);
        }

        //구매 제안자의 경우
        else if(!negotiationEntityList.isEmpty()) {
            Pageable pageable = PageRequest.of(page, 25, Sort.by("id"));
            Page<NegotiationEntity> proposalEntityPage = negotiationRepository.findAllByItemIdAndWriterAndPassword(itemId, writer, password, pageable);
            return proposalEntityPage.map(NegotiationDto::fromEntity);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /items/{itemId}/proposals/{proposalId}
    public NegotiationDto updateNegotiation(Long itemId, Long proposalId, NegotiationDto negotiationDto) {
        Optional<NegotiationEntity> optionalNegotiation = negotiationRepository.findById(proposalId);
        if (optionalNegotiation.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        NegotiationEntity updateNegotiation = optionalNegotiation.get();
        // itemId 체크
        if (!updateNegotiation.getItemId().equals(itemId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 작성자, 비밀번호 체크
        if (!updateNegotiation.getWriter().equals(negotiationDto.getWriter()) || !updateNegotiation.getPassword().equals(negotiationDto.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 가격만 수정
        updateNegotiation.setSuggestedPrice(negotiationDto.getSuggestedPrice());
        return NegotiationDto.fromEntity(negotiationRepository.save(updateNegotiation));
    }

    // 제안상태 체크 => 수락, 거절
    public NegotiationDto acceptNegotiation(Long itemId, Long proposalId, NegotiationDto dto){

        Optional<NegotiationEntity> optionalNegotiation = negotiationRepository.findById(proposalId);
        if (optionalNegotiation.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // itemId 체크
        NegotiationEntity negotiationEntity = optionalNegotiation.get();
        if (!negotiationEntity.getItemId().equals(itemId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // itemId로 ItemEntity 값 불러오기
        Optional<ItemEntity> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // 해당 물품의 작성자, 비밀번호 체크
        ItemEntity itemEntity = optionalItem.get();
        if (!itemEntity.getWriter().equals(dto.getWriter()) || !itemEntity.getPassword().equals(dto.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 제안상태 변경
        negotiationEntity.setStatus(dto.getStatus());

        return NegotiationDto.fromEntity(negotiationRepository.save(negotiationEntity));
    }

    // 제안상태 체크 => 수락체크, 확정, 판매완료, 거절
    public NegotiationDto confirmNegotiation (Long itemId, Long proposalId, NegotiationDto dto){

        Optional<NegotiationEntity> optionalNegotiation = negotiationRepository.findById(proposalId);
        if (optionalNegotiation.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // itemId 체크
        NegotiationEntity negotiationEntity = optionalNegotiation.get();
        if (!negotiationEntity.getItemId().equals(itemId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 구매제안 등록자, 작성자와 비밀번호 체크
        if (!negotiationEntity.getWriter().equals(dto.getWriter()) || !negotiationEntity.getPassword().equals(dto.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 제안상태가 수락 아닐경우 => "Fail"
        if (!negotiationEntity.getStatus().equals("수락")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 제안상태 => "confirm"
        negotiationEntity.setStatus(dto.getStatus());
        NegotiationEntity confirmNegotiation = negotiationRepository.save(negotiationEntity);

        // itemId로 ItemEntity 값 불러오기
        Optional<ItemEntity> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // 물품상태 => "판매완료"
        ItemEntity item = optionalItem.get();
        item.setStatus("판매완료");

        // 나머지 제안상태 => "refuse"
        for(NegotiationEntity refuse : negotiationRepository.findAllByItemId(itemId)){
            if (refuse.getId().equals(proposalId))
                continue;
            refuse.setStatus("거절");
            negotiationRepository.save(refuse);
        }
        return NegotiationDto.fromEntity(confirmNegotiation);

    }

    // DELETE /items/{itemId}/proposals/{proposalId}
    public void deleteNegotiation(Long itemId, Long proposalId, NegotiationDto negotiationDto) {
        if(!itemRepository.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<NegotiationEntity> optionalNegoEntity = negotiationRepository.findById(proposalId);
        if(optionalNegoEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        NegotiationEntity negoEntity = optionalNegoEntity.get();

        if(!negoEntity.getWriter().equals(negotiationDto.getWriter()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if(!negoEntity.getPassword().equals(negotiationDto.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        negotiationRepository.deleteById(proposalId);
    }
}
