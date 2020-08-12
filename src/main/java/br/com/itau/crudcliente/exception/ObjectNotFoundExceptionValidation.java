package br.com.itau.crudcliente.exception;

import javax.validation.ConstraintDeclarationException;

public class ObjectNotFoundExceptionValidation extends ConstraintDeclarationException {
    public ObjectNotFoundExceptionValidation(String message) {
        super(message);
    }

    public ObjectNotFoundExceptionValidation(String message, Throwable cause) {
        super(message, cause);
    }
}
