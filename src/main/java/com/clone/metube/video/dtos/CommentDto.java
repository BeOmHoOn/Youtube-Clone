package com.clone.metube.video.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Long videoId;
    private Long authorId;
    private Long parentCommentId;
    private String content;
    private LocalDateTime createdAt;
}
