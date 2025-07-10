package com.clone.metube.authentication.normal.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
