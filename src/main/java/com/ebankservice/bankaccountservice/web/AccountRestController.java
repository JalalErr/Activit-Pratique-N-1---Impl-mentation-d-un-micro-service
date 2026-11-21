package com.ebankservice.bankaccountservice.web;

import com.ebankservice.bankaccountservice.entities.BankAccount;
import com.ebankservice.bankaccountservice.repositories.BankAccountRepositorie;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    BankAccountRepositorie bankAccountRepositorie;

    public AccountRestController(BankAccountRepositorie bankAccountRepositorie) {
        this.bankAccountRepositorie = bankAccountRepositorie;
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
    public BankAccount save(@RequestBody BankAccount bankaccount) {
        if (bankaccount.getId() == null) bankaccount.setId(UUID.randomUUID().toString());
        return bankAccountRepositorie.save(bankaccount);
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
