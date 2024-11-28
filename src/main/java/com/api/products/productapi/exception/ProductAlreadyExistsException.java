package com.api.products.productapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
