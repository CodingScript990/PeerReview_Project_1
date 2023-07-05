package com.likelion.market.controller;

import com.likelion.market.dto.ItemDto;
import com.likelion.market.dto.ResponseDto;
import com.likelion.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    // Member Field
    private final ItemService service;

    // POST /items
    @PostMapping
    public ResponseDto create(@RequestBody ItemDto itemDto) {
        service.createItem(itemDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("등록이 완료되었습니다.");
        return response;
    }

    // GET /items
    @GetMapping
    public Page<ItemDto> readAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "25") Integer limit) {
        return service.readItemsPaged(page, limit);
    }

    // GET /items/{itemId}
    @GetMapping("/{itemId}")
    public ItemDto read(@PathVariable("itemId") Long id) {
        return service.readItem(id);
    }

    // PUT /items/{itemId}
    @PutMapping("/{itemId}")
    public ResponseDto update(@PathVariable("itemId") Long id, @RequestBody ItemDto itemDto) {
        service.updateItem(id, itemDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("물품이 수정되었습니다.");
        return response;
    }

    // PUT /items/{itemId}/image
    @PutMapping(value = "/{itemId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto updateImage(@PathVariable("itemId") Long id, @RequestParam("image") MultipartFile image, @RequestParam("writer") String writer, @RequestParam("password") String password) {
        service.updateUserImage(id, image, writer, password);
        ResponseDto response = new ResponseDto();
        response.setMessage("이미지 등록이 완료되었습니다.");
        return response;
    }

    // DELETE /items/{itemId}
    @DeleteMapping("/{itemId}")
    public ResponseDto delete(@PathVariable("itemId") Long id, @RequestBody ItemDto itemDto) {
        service.deleteItem(id, itemDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("물품을 삭제했습니다.");
        return response;
    }
}
