package com.MyTruckApp.exception_handler;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String message) {
        super(message);
    }
}
