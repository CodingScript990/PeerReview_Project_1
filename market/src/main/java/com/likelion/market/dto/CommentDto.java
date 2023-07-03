package com.likelion.market.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likelion.market.entity.CommentEntity;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {
    // Member Filed
    private Long id;
    private Long itemId;
    private String writer;
    private String password;
    private String content;
    private String reply;

    // Constructor
    public static CommentDto fromEntity(CommentEntity entity) {
        CommentDto dto = new CommentDto();

        dto.setId(entity.getId());
        dto.setItemId(entity.getItemId());
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setContent(entity.getContent());
        dto.setReply(entity.getReply());

        return dto;
    }
}
