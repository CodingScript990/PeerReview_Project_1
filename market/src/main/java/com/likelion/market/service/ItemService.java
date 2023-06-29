package com.likelion.market.service;

import com.likelion.market.dto.ItemDto;
import com.likelion.market.entity.ItemEntity;
import com.likelion.market.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    // Member Field
    private final ItemRepository repository;

    // Create
    public ItemDto createItem(ItemDto dto) {
        ItemEntity newItem = new ItemEntity();

        newItem.setTitle(dto.getTitle());
        newItem.setDescription(dto.getDescription());
        newItem.setImageUrl(dto.getImageUrl());
        newItem.setMinPriceWanted(dto.getMinPriceWanted());
        newItem.setStatus("판매중");
        newItem.setWriter(dto.getWriter());
        newItem.setPassword(dto.getPassword());

        log.info(String.valueOf(newItem));

        return ItemDto.fromEntity(repository.save(newItem));
    }

    // Read => Items
    public ItemDto readItem(Long id) {
        Optional<ItemEntity> optionalItem = repository.findById(id);
        return ItemDto.fromEntity(optionalItem.get());
    }

    // Update => Title, Description, MinPriceInteger, Status, Writer, Password
    public ItemDto updateItem(Long id, ItemDto dto) {
        Optional<ItemEntity> updateItem = repository.findById(id);
        if (updateItem.isPresent()) {
            ItemEntity target = updateItem.get();

            // 비밀번호가 일치하면 수정하기
            if (target.getPassword().equals(dto.getPassword())) {
                target.setTitle(dto.getTitle());
                target.setDescription(dto.getDescription());
                target.setMinPriceWanted(dto.getMinPriceWanted());
                target.setStatus(dto.getStatus());
                target.setWriter(dto.getWriter());
                target.setPassword(dto.getPassword());

                return ItemDto.fromEntity(repository.save(target));
            }
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Update => Image
    public ItemDto updateUserImage(Long id, ItemDto dto) {
        Optional<ItemEntity> imageItem = repository.findById(id);
        if (imageItem.isPresent()) {
            ItemEntity target = imageItem.get();

            // 비밀번호가 일치하면 수정하기
            if (target.getPassword().equals(dto.getPassword())) {
                target.setImageUrl(dto.getImageUrl());
                return ItemDto.fromEntity(repository.save(target));
            }
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Delete
    public void deleteItem(Long id, ItemDto dto) {
        Optional<ItemEntity> optionalItemEntity = repository.findById(id);

        if (optionalItemEntity.isPresent()) {
            ItemEntity target = optionalItemEntity.get();
            // 비밀번호가 일치하면 삭제하기
            if (target.getPassword().equals(dto.getPassword())) repository.deleteById(id);
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Page
    public Page<ItemDto> readItemsPaged(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<ItemEntity> itemEntityPage = repository.findAll(pageable);
        Page<ItemDto> itemDtoPage = itemEntityPage.map(ItemDto::fromEntity);

        return itemDtoPage;
    }
}
