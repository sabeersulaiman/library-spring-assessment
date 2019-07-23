package com.ssabeer.libraryspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotIssuedException extends RuntimeException {
    public BookNotIssuedException() {
        super("The book you are trying to return is not yet issued.");
    }
}
