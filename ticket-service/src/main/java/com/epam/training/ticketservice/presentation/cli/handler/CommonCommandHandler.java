package com.epam.training.ticketservice.presentation.cli.handler;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
public class CommonCommandHandler implements Quit.Command {

    @ShellMethod(value = "Exit the shell.", key = { "quit", "exit", "terminate" })
    public void quit() {
        System.exit(0);
    }
}
