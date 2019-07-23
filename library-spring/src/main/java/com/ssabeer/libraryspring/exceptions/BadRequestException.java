package com.ssabeer.libraryspring.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    @Getter
    private String resourceName;
    @Getter
    private String fieldName;
    @Getter
    private Object fieldValue;

    public BadRequestException( String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s cannot take value %s for property '%s'", resourceName, fieldValue, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
