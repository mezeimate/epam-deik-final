package com.epam.training.ticketservice.repository;

import java.util.Optional;

import com.epam.training.ticketservice.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findAccountByNameAndPassword(String name, String password);
}
