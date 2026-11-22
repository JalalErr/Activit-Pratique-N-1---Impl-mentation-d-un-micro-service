package com.ebankservice.bankaccountservice.dto;

import com.ebankservice.bankaccountservice.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class BankAccountRequestDTO {

    private Double balance;
    private String currency;
    private AccountType accountType;
}

