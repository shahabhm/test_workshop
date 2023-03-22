package org.example.data;

import org.example.data.model.Account;
import org.example.data.model.Book;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Map;

public class Data {
    public Data() {
        this.accounts = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    ArrayList<Account> accounts;
    ArrayList<Book> books;

    public Account findAccountBySSN(String ssn){
        for (Account account : accounts) {
            if(account.getSsn() == ssn){
                return account;
            }
        }
        return null;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }
}
