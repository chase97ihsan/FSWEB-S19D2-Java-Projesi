package com.workintech.S7D2.service;

import com.workintech.S7D2.dao.AccountRepository;
import com.workintech.S7D2.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@NoArgsConstructor
public class AccountServiceImpl implements AccountService{
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(int id) {
        Optional<Account> optional=accountRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account delete(int id) {
        Account account=findById(id);
        accountRepository.delete(account);
        return account;
    }
}
