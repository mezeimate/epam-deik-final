package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Account;

import java.util.Optional;

public interface AccountService {

    Account save(Account account);

    Optional<Account> findAccountByNameAndPassword(String name, String password);

}
