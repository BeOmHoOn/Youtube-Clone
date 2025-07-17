package com.clone.metube.authentication.oauth2.entities;

import com.clone.metube.authentication.interfaces.AccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2Account extends AccountEntity {
    private String provider;

    private String providerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OAuth2AccountDetails details;
}
