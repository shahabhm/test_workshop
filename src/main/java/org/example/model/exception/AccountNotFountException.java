package org.example.model.exception;

public class AccountNotFountException extends Exception{
    public AccountNotFountException(){
        super("this account does not exist");
    }
}
