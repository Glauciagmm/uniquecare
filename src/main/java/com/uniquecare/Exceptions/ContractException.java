package com.uniquecare.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ContractException extends Exception{
    private static final long serialVersionUID = -4732903185999204328L;

    public ContractException(String message) {
        super(message);
    }
}
