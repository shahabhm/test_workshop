package org.example.model.exception;

public class AlreadySignedUpException extends Exception{
    public AlreadySignedUpException() {
        super("an account with this number already exists");
    }
}
