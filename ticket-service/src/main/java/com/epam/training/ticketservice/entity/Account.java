package com.epam.training.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}
