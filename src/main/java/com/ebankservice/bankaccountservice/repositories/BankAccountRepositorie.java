package com.ebankservice.bankaccountservice.repositories;

import com.ebankservice.bankaccountservice.entities.BankAccount;
import com.ebankservice.bankaccountservice.enums.AccountType;
import com.ebankservice.bankaccountservice.web.AccountRestController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface BankAccountRepositorie extends JpaRepository<BankAccount, String> {

    List<BankAccount> findByAccountType(AccountType accountType);
}
