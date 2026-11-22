package com.ebankservice.bankaccountservice.service;

import com.ebankservice.bankaccountservice.dto.BankAccountRequestDTO;
import com.ebankservice.bankaccountservice.dto.BankAccountResponseDTO;
import com.ebankservice.bankaccountservice.entities.BankAccount;
import com.ebankservice.bankaccountservice.mappers.AccountMapper;
import com.ebankservice.bankaccountservice.repositories.BankAccountRepositorie;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private BankAccountRepositorie bankAccountRepositorie;
    private AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepositorie bankAccountRepositorie, AccountMapper accountMapper) {
        this.bankAccountRepositorie = bankAccountRepositorie;
        this.accountMapper = accountMapper;
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {

        BankAccount bankAccount =  BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepositorie.save(bankAccount);

        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
