package com.clone.metube.authentication.repositories;

import com.clone.metube.authentication.entitiy.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
}
