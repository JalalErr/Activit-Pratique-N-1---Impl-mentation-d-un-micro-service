package com.ebankservice.bankaccountservice.service;

import com.ebankservice.bankaccountservice.dto.BankAccountResponseDTO;
import com.ebankservice.bankaccountservice.dto.BankAccountRequestDTO;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

}
