package com.ourbank.repository;

import com.ourbank.entity.AccountType;
import com.ourbank.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

    List<BankAccount> findByAccountType(AccountType accountType);
}
