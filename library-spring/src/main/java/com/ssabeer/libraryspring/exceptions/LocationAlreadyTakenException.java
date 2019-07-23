package com.ssabeer.libraryspring.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LocationAlreadyTakenException extends RuntimeException {
    public LocationAlreadyTakenException() {
        super("The book location is already being used by another book. Please try with a different location.");
    }
}
