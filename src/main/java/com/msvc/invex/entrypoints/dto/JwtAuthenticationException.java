package com.msvc.invex.entrypoints.dto;


public class JwtAuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JwtAuthenticationException(String message) {
        super(message);
    }
}