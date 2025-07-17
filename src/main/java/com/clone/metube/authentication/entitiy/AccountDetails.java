package com.clone.metube.authentication.entitiy;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private String nickName;

}
