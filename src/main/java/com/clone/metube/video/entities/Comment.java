package com.clone.metube.video.entities;

import com.clone.metube.authentication.entitiy.Account;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;

    private Long parentCommentId;

    private String content;

    private LocalDateTime createdAt;

    @PostConstruct
    public void init() {
        this.createdAt = LocalDateTime.now();
    }
}
