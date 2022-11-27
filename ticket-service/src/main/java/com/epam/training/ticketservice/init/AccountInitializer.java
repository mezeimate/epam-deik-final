package com.epam.training.ticketservice.init;

import javax.annotation.PostConstruct;

import com.epam.training.ticketservice.common.TicketConstants;
import com.epam.training.ticketservice.entity.Account;
import com.epam.training.ticketservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountInitializer {

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void initAdminAccounts() {
        accountRepository.save(new Account(TicketConstants.ADMIN, TicketConstants.ADMIN));
        log.info("Saving admin account");
    }
}
