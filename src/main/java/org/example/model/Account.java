package org.example.model;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.util.ArrayList;
import java.util.HashSet;

public class Account extends Savable {


    @NotNull
    @NotEmpty
    @Length(max=50)
    String firstName;
    @NotNull
    @NotEmpty
    @Length(max=50)
    String lastName;
    @NotNull
    @NotEmpty
    @Length(max=10, min = 10)
    String ssn;
    int balance;
    HashSet<Book> borrowedBooks;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public HashSet<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(HashSet<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public int getBalance() {
        return balance;
    }

    public Account(String firstName, String lastName, String ssn, int balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.balance = balance;
        this.borrowedBooks = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", balance=" + balance +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
