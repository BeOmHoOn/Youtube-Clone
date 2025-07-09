package com.clone.metube.authentication.oauth2.entities;

import com.clone.metube.authentication.normal.entities.Account;
import jakarta.persistence.*;

@Entity
public class OAuth2AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @OneToOne
    @JoinColumn(name = "oAuth2Account_id")
    private OAuth2Account oAuth2Account;
}
