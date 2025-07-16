package com.clone.metube.video.repositories;

import com.clone.metube.video.entities.Comment;
import com.clone.metube.video.entities.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.provider.QueryComment;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findCommentsByVideoId(Long videoId) {
        QComment comment = QComment.comment;

        return queryFactory
                .selectFrom(comment)
                .where(comment.video.id.eq(videoId))
                .fetch();
    }
}
