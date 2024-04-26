package com.scaler.firstspringapi.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAlreadyExistsException extends Exception {
    private String message;

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
