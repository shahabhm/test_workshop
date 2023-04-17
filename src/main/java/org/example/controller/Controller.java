package org.example.controller;

import org.example.data.Data;
import org.example.model.Account;
import org.example.controller.validation.ValidationException;
import org.example.controller.validation.Validator;

public class Controller {
    Data data;
    public Controller (){
        this.data = new Data();
    }

    public String createAccount(String firstName, String lastName, String ssn){
        try {
            Validator.validateSignUp(firstName, lastName, ssn);
        }catch (Exception e){
            if (e instanceof ValidationException){
                return e.getMessage();
            }
            System.err.println(e.getMessage());
            return "sorry, something went wrong on our side...";
        }
        Account existingAccount = data.findAccountBySSN(ssn);
        if (existingAccount != null){
            return "you have already signed up...";
        }
        Account newAccount = new Account(firstName, lastName, ssn, 0);
        data.addAccount(newAccount);
        return "success";
    }
}
