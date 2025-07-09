package com.clone.metube.authentication.oauth2.entities;

import com.clone.metube.global.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.el.parser.AstFalse;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String email;

    private String provider;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OAuth2AccountDetails details;

    @Column(updatable = false)
    LocalDateTime createdAt;

    @Column
    LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
