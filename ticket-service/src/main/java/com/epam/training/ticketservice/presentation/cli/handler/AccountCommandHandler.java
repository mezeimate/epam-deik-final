package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.common.TicketConstants;
import com.epam.training.ticketservice.entity.Account;
import com.epam.training.ticketservice.helper.AccountHelper;
import com.epam.training.ticketservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
public class AccountCommandHandler {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountHelper accountHelper;

    @ShellMethod(value = "Sign In", key = "sign in privileged")
    public String signIn(final String name, final String passwd) {
        Optional<Account> account = accountService.findAccountByNameAndPassword(name, passwd);
        if (account.isPresent()) {
            accountHelper.setSignedAccount(account);
            return "You are signed in";
        } else {
            return "Login failed due to incorrect credentials";
        }
    }

    @ShellMethod(value = "Sign Out", key = "sign out")
    public void signOut() {
        accountHelper.signOutAccount();
    }

    @ShellMethod(value = "Describe Account", key = "describe account")
    public String describeAccount() {
        Optional<Account> signedAccount = accountHelper.getSignedAccount();
        if (signedAccount.isPresent()) {
            return String.format("Signed in with privileged account '%s'", signedAccount.get().getName());
        } else {
            return TicketConstants.UNAUTHORIZED;
        }
    }

}