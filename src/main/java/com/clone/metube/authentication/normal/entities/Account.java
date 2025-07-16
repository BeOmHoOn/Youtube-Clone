package com.clone.metube.authentication.normal.entities;

import com.clone.metube.authentication.interfaces.AccountEntity;
import com.clone.metube.global.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AccountEntity {
    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private AccountDetails accountDetails;
}
