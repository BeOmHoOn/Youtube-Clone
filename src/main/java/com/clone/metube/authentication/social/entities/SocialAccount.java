package com.clone.metube.authentication.social.entities;

import com.clone.metube.global.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String provider;
    private String providerId;

    @OneToOne(mappedBy = "socialAccount",  cascade = CascadeType.ALL)
    private SocialAccountDetails socialAccountDetails;
}
