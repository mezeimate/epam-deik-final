package com.epam.training.ticketservice.helper;

import com.epam.training.ticketservice.common.TicketConstants;
import com.epam.training.ticketservice.entity.Account;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountHelper {

    private Optional<Account> signedAccount = Optional.empty();

    public Optional<Account> getSignedAccount() {
        return signedAccount;
    }

    public boolean isAdmin() {
        if (signedAccount.isPresent()) {
            final String name = signedAccount.get().getName();
            final String password = signedAccount.get().getPassword();
            return name.equals(TicketConstants.ADMIN) && password.equals(TicketConstants.ADMIN);
        }
        return false;
    }

    public void setSignedAccount(Optional<Account> signedAccount) {
        this.signedAccount = signedAccount;
    }

    public void signOutAccount() {
        signedAccount = Optional.empty();
    }
}
