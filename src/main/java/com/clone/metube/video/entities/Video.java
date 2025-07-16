package com.clone.metube.video.entities;

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
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String localAuthorId;

    private String socialAuthorId;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String title;

    private String description;

    private int length;

    private LocalDateTime createdAt;

    @PostConstruct
    public void init() {
        this.createdAt = LocalDateTime.now();
    }
}
