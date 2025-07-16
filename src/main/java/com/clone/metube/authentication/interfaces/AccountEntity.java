package com.clone.metube.authentication.interfaces;

import com.clone.metube.global.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public abstract class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(unique = true)
    protected String email;

    @Enumerated(EnumType.STRING)
    protected UserRole role;

    @Column(updatable = false)
    protected LocalDateTime createdAt;

    @PrePersist
    public  void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
