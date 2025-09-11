package com.monicah.Umoja_Welfare.Exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberExistException extends RuntimeException {
    private final String message;
    @Override
    public String getMessage() {
        return message; // Return the custom message
    }
}
