package com.clone.metube.video.repositories;

import com.clone.metube.video.entities.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findCommentsByVideoId(Long videoId);
}
