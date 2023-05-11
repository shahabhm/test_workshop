package org.example.model.exception;

public class BookDoesNotExistException extends Exception{
    public BookDoesNotExistException() {
        super("there is no book with this name.");
    }
}
