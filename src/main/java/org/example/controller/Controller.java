package org.example.controller;

;
import org.example.model.Account;
import org.example.model.Book;
import org.example.model.DataBase;
import org.example.model.exception.AccountNotFountException;
import org.example.model.exception.AlreadySignedUpException;
import org.example.model.exception.BookDoesNotExistException;

import java.util.Objects;


public class Controller {
    DataBase db;

    public Controller() {
        db = DataBase.getInstance();
    }

    public String createAccount(String firstName, String lastName, String ssn) throws Exception {
        Account existingAccount = db.findAccountBySSN(ssn);
        if (existingAccount != null) {
            throw new AlreadySignedUpException();
        }
        Account newAccount = new Account(firstName, lastName, ssn, 0);
        db.save(newAccount);
        return "success";
    }

    public String createBook(String name, String genre, int totalStock) throws Exception {
        Book book = new Book(name, genre, totalStock, totalStock);
        db.save(book);
        return "success";
    }

    public String borrowBook(String borrowerSsn, String bookName) throws Exception {
        Book book = db.findBookByName(bookName);
        if (Objects.isNull(book)) {
            throw new BookDoesNotExistException();
        }
        Account borrower = db.findAccountBySSN(borrowerSsn);
        if (Objects.isNull(borrower)) {
            throw new AccountNotFountException();
        }
        if (book.getAvailableStock() < 1) {
            return "there is no copy available";
        }
        book.setAvailableStock(book.getAvailableStock() - 1);
        borrower.getBorrowedBooks().add(book);
        return "success";
    }

    public String returnBook(String borrowerSsn, String bookName) throws Exception {
        Book borrowedBook = db.findBookByName(bookName);
        if (Objects.isNull(borrowedBook)) {
            throw new BookDoesNotExistException();
        }
        Account borrower = db.findAccountBySSN(borrowerSsn);
        if (Objects.isNull(borrower)) {
            throw new BookDoesNotExistException();
        }
        if (!borrower.getBorrowedBooks().contains(borrowedBook)) {
            return "book is not borrowed by this person";
        }
        borrower.getBorrowedBooks().remove(borrowedBook);
        borrowedBook.setAvailableStock(borrowedBook.getAvailableStock() + 1);
        return "success";
    }


}
