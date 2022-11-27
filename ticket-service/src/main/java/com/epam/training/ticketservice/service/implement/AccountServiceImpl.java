package com.epam.training.ticketservice.service.implement;

import com.epam.training.ticketservice.entity.Account;
import com.epam.training.ticketservice.repository.AccountRepository;
import com.epam.training.ticketservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountByNameAndPassword(String name, String password) {
        return accountRepository.findAccountByNameAndPassword(name, password);
    }

}
