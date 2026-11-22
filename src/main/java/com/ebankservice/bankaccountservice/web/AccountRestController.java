package com.ebankservice.bankaccountservice.web;

import com.ebankservice.bankaccountservice.BankAccountServiceApplication;
import com.ebankservice.bankaccountservice.dto.BankAccountRequestDTO;
import com.ebankservice.bankaccountservice.dto.BankAccountResponseDTO;
import com.ebankservice.bankaccountservice.entities.BankAccount;
import com.ebankservice.bankaccountservice.mappers.AccountMapper;
import com.ebankservice.bankaccountservice.repositories.BankAccountRepositorie;
import com.ebankservice.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private BankAccountRepositorie bankAccountRepositorie;
    private AccountService  accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepositorie bankAccountRepositorie, AccountService accountService) {
        this.bankAccountRepositorie = bankAccountRepositorie;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankccount() {
        return bankAccountRepositorie.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankccount(@PathVariable String id) {
            return bankAccountRepositorie.findById(id)
                    .orElseThrow(()-> new RuntimeException(String.format("Bankaccount with id %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO RequestDTO) {
        return accountService.addAccount(RequestDTO);
    }

    @PostMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepositorie.findById(id).orElseThrow();
        if (bankAccount.getBalance() != null ) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt() != null ) account.setCreatedAt((new Date()));
        if (bankAccount.getAccountType() != null ) account.setAccountType(bankAccount.getAccountType());
        if (bankAccount.getCurrency() != null ) account.setCurrency(bankAccount.getCurrency());

        return bankAccountRepositorie.save(account);
    }

    @DeleteMapping("/bankAccount/{id}")
    public void delete(@PathVariable String id) {
        bankAccountRepositorie.deleteById(id);
    }
}
