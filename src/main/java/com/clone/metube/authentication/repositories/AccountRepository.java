package com.clone.metube.authentication.repositories;

import com.clone.metube.authentication.entitiy.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmailAndPassword(String email, String password);
    Optional<Account> findByEmail(String email);
}
