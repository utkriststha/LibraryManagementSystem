package com.company;

public class ISBNFormatError extends Exception {

    //Creating Exception for error isbn input and displaying the message from the argument
    public ISBNFormatError(String message) {
        super(message);
    }
}
