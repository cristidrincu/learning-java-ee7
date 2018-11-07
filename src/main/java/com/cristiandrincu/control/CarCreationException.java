package com.cristiandrincu.control;

import javax.ejb.ApplicationException;

@ApplicationException //do not wrap this exception into an EJB exception
public class CarCreationException extends RuntimeException {

    public CarCreationException(String message) {
        super(message);
    }
}
