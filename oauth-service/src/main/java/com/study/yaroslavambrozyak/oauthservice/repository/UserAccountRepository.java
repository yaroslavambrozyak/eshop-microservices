package com.study.yaroslavambrozyak.oauthservice.repository;

import com.study.yaroslavambrozyak.oauthservice.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount,UUID> {

    Optional<UserAccount> findByEmail(String email);

}
