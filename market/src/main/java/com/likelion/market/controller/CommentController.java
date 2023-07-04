package com.likelion.market.controller;

import com.likelion.market.dto.CommentDto;
import com.likelion.market.dto.ResponseDto;
import com.likelion.market.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/comments")
public class CommentController {
    // Service => Comment
    private final CommentService commentService;

    // POST /items/{itemId}/comments => 200[OK]
    @PostMapping
    public ResponseDto commentCreate(@PathVariable("itemId") Long itemId, @RequestBody
    CommentDto commentDto) {
        commentService.createComment(itemId, commentDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("댓글이 등록되었습니다.");
        return response;
    }

    // GET /items/{itemId}/comments => 200[OK]
    @GetMapping
    public Page<CommentDto> commentReadAll(@PathVariable("itemId") Long itemId, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "25") Integer limit) {
        return commentService.readCommentsAll(itemId, page, limit);
    }

    // PUT /items/{itemId}/comments/{commentId} => 200[OK]
    @PutMapping("/{commentId}")
    public ResponseDto commentUpdate(@PathVariable("itemId") Long itemId, @PathVariable("commentId") Long commentId, @RequestBody CommentDto commentDto) {
        commentService.updateComment(itemId, commentId, commentDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("댓글이 수정되었습니다.");
        return response;
    }

    // PUT /items/{itemId}/comments/{commentId}/reply => 200[OK]
    @PutMapping("/{commentId}/reply")
    public ResponseDto commentReply(@PathVariable("itemId") Long itemId, @PathVariable("commentId") Long commentId, @RequestBody CommentDto commentDto) {
        commentService.updateReplyComment(itemId, commentId, commentDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("댓글에 답변이 추가되었습니다.");
        return response;
    }

    // DELETE /items/{itemId}/comments/{commentId} => 200[OK]
    @DeleteMapping("/{commentId}")
    public ResponseDto commentDelete(@PathVariable("itemId") Long itemId, @PathVariable("commentId") Long commentId, @RequestBody CommentDto commentDto) {
        commentService.deleteComment(itemId, commentId, commentDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("댓글을 삭제했습니다.");
        return response;
    }
}
