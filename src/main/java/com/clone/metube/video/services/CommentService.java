package com.clone.metube.video.services;

import com.clone.metube.authentication.repositories.AccountRepository;
import com.clone.metube.video.dtos.CommentDto;
import com.clone.metube.video.dtos.CommentListRequest;
import com.clone.metube.video.repositories.CommentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private JPAQueryFactory queryFactory;
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;

    public List<CommentDto> getAllComments(CommentListRequest commentListRequest) {
        return commentRepository.findCommentsByVideoId(commentListRequest.getVideoId())
                .stream().map(
                        (comment) -> CommentDto.builder()
                                .id(comment.getId())
                                .videoId(comment.getVideo().getId())
                                .authorId(comment.getAuthor().getId())
                                .parentCommentId(comment.getParentCommentId())
                                .content(comment.getContent())
                                .createdAt(comment.getCreatedAt())
                                .build()
                ).toList();
    }
}
