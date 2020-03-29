package com.bridgeLabzs.exception;

public class CSVBuilderException extends Exception {
    public CSVBuilderException.ExceptionType exceptionType;

    public CSVBuilderException(String message, CSVBuilderException.ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType {
        UNABLE_TO_PARSE
    }

}
