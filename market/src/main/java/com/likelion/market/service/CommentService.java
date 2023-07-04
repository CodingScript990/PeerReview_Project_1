package com.likelion.market.service;

import com.likelion.market.dto.CommentDto;
import com.likelion.market.entity.CommentEntity;
import com.likelion.market.entity.ItemEntity;
import com.likelion.market.repository.CommentRepository;
import com.likelion.market.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    // Call Repository
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    // POST /items/{itemId}/comments
    public CommentDto createComment(Long itemId, CommentDto commentDto) {

        if (!itemRepository.existsById(itemId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity newComment = new CommentEntity();

        newComment.setItemId(itemId);// 대상 물품
        newComment.setContent(commentDto.getContent()); // 댓글 내용
        newComment.setWriter(commentDto.getWriter()); // 작성자
        newComment.setPassword(commentDto.getPassword()); // 비밀번호

        log.info(String.valueOf(newComment));

        return CommentDto.fromEntity(commentRepository.save(newComment));
    }

    // GET /items/{itemId}/comments => 전체 조회
    public Page<CommentDto> readCommentsAll(Long itemId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CommentEntity> readEntities = commentRepository.findByItemId(itemId, pageable);
        return readEntities.map(CommentDto :: fromEntity);
    }

    // PUT /items/{itemId}/comments/{commentId}
    public CommentDto updateComment(Long itemId, Long commentId, CommentDto commentDto) {
        Optional<CommentEntity> updateComment = commentRepository.findById(commentId);

        // 댓글이 존재하는지를 확인하기!
        if (!itemRepository.existsById(itemId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // 댓글이 존재하면 비밀번호를 체크하기!
        if (!commentDto.getPassword().equals(updateComment.get().getPassword())) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity target = updateComment.get();

        target.setContent(commentDto.getContent());

        commentRepository.save(target);

        return CommentDto.fromEntity(target);
    }

    // PUT /items/{itemId}/comments/{commentId}/reply
    public CommentDto updateReplyComment(Long itemId, Long commentId, CommentDto commentDto) {
        Optional<CommentEntity> replyComment = commentRepository.findById(commentId);

        if (!itemRepository.existsById(itemId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<ItemEntity> inspectItem = itemRepository.findById(replyComment.get().getItemId());

        if (!commentDto.getPassword().equals(inspectItem.get().getPassword())) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity target = replyComment.get();

        target.setReply(commentDto.getReply());

        commentRepository.save(target);

        return CommentDto.fromEntity(target);
    }

    // DELETE /items/{itemId}/comments/{commentId}
    public void deleteComment(Long itemId, Long commentId, CommentDto commentDto) {
        Optional<CommentEntity> deleteEntity = commentRepository.findById(commentId);

        if (!itemRepository.existsById(itemId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!commentDto.getPassword().equals(deleteEntity.get().getPassword())) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        commentRepository.deleteById(commentId);
    }
}
