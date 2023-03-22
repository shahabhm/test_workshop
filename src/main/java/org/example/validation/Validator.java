package org.example.validation;

public class Validator {

    public static void validateSignUp(String firstName, String lastName, String ssn) throws ValidationException {
        // could also use `assert` here with a try-catch
        if(firstName.length() > 50 || firstName.length()<2){
            throw new ValidationException("first name must be between 2 and 50 characters");
        }
        if(lastName.length() > 50 || lastName.length()<2){
            throw new ValidationException("last name must be between 2 and 50 characters");
        }
        if(!ssn.matches("^[0-9]{10}$")){
            throw new ValidationException("ssn must be 10 digits");
        }
    }
}
