package com.ebankservice.bankaccountservice;

import com.ebankservice.bankaccountservice.entities.BankAccount;
import com.ebankservice.bankaccountservice.enums.AccountType;
import com.ebankservice.bankaccountservice.repositories.BankAccountRepositorie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepositorie bankAccountRepositorie) {
        return args -> {
            for (int i = 1; i <= 10; i++) {
                BankAccount bankAccount =  BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .accountType(Math.random() > 0.5? AccountType.CURRENTE_ACCOUNT:AccountType.SAVING_ACCOUNT)
                        .balance(10000 + Math.random() * 90000)
                        .createdAt(new Date())
                        .currency("MAD")
                        .build();
                bankAccountRepositorie.save(bankAccount);
            }
        };
    }
}
