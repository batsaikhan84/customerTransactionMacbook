package com.customer.relationship.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerTransactionNotFoundException extends RuntimeException {
    public CustomerTransactionNotFoundException() {
        super();
    }
    public CustomerTransactionNotFoundException(String message) {
        super(message);
    }
    public CustomerTransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
