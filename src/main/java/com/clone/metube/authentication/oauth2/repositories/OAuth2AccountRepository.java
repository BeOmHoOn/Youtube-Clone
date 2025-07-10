package com.clone.metube.authentication.oauth2.repositories;

import com.clone.metube.authentication.oauth2.entities.OAuth2Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuth2AccountRepository extends JpaRepository<OAuth2Account, Long> {
    Optional<OAuth2Account> findByEmail(String email);
}
