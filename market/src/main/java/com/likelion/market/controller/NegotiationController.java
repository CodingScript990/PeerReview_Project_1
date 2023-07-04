package com.likelion.market.controller;

import com.likelion.market.dto.NegotiationDto;
import com.likelion.market.dto.ResponseDto;
import com.likelion.market.service.NegotiationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/proposals")
public class NegotiationController {
    // Service => Negotiation
    private final NegotiationService negotiationService;

    // POST /items/{itemId}/proposals
    @PostMapping
    public ResponseDto negotiationCreate(@PathVariable("itemId") Long itemId, @RequestBody  NegotiationDto NegotiationDto) {
        negotiationService.createNegotiation(itemId, NegotiationDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("구매 제안이 등록되었습니다.");
        return response;
    }

    // GET /items/{itemId}/proposals?writer=jeeho.edu&password=qwerty1234&page=1
    @GetMapping
    public Page<NegotiationDto> negotiationReadAll(@PathVariable("itemId") Long itemId, @RequestParam("writer") String writer, @RequestParam("password") String password, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return negotiationService.readNegotiation(itemId, writer, password, page);
    }

    // PUT /items/{itemId}/proposals/{proposalId}
    @PutMapping("/{proposalId}")
    public ResponseDto negotiationUpdate(@PathVariable("itemId") Long itemId, @PathVariable("proposalId") Long proposalId, @RequestBody NegotiationDto negotiationDto) {
        // 제안 => 수정
        if (negotiationDto.getStatus() == null) {
            negotiationService.updateNegotiation(itemId, proposalId, negotiationDto);
            ResponseDto response = new ResponseDto();
            response.setMessage("제안이 수정되었습니다.");
            return response;
        }
        // 제안 => 확정
        else if (negotiationDto.getStatus().equals("확정")) {
            negotiationService.confirmNegotiation(itemId, proposalId, negotiationDto);
            ResponseDto response = new ResponseDto();
            response.setMessage("구매가 확정되었습니다.");
            return response;
        }
        // 제안 => 수락, 거절
        else {
            negotiationService.acceptNegotiation(itemId, proposalId, negotiationDto);
            ResponseDto response = new ResponseDto();
            response.setMessage("제안의 상태가 변경되었습니다.");
            return response;
        }
    }

    // DELETE /items/{itemId}/proposals/{proposalId}
    @DeleteMapping("/{proposalId}")
    public ResponseDto negotiationDelete(@PathVariable("itemId") Long itemId, @PathVariable("proposalId") Long proposalId, @RequestBody NegotiationDto negotiationDto) {
        negotiationService.deleteNegotiation(itemId, proposalId, negotiationDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("제안을 삭제했습니다.");
        log.info(negotiationDto.toString());
        return response;
    }
}
