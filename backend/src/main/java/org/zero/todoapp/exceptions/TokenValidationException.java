package org.zero.todoapp.exceptions;

import java.security.InvalidKeyException;

public class TokenValidationException extends Exception {
    public TokenValidationException(InvalidKeyException e1) {
        super(e1);
    }
}
