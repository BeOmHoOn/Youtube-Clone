package com.clone.metube.authentication.entitiy;

import com.clone.metube.global.enums.AccountRole;
import com.clone.metube.global.enums.AccountStatus;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false)
    private boolean isOAuth2Account;

    @Column(nullable = false, unique = true)
    protected String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected AccountRole role;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private AccountDetails accountDetails;

    @Column(updatable = false)
    protected LocalDateTime createdAt;

    @PostConstruct
    public void init() {
        this.createdAt = LocalDateTime.now();
    }
}
