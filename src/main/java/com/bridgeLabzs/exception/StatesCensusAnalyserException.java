package com.bridgeLabzs.exception;

public class StatesCensusAnalyserException extends Exception {
    public ExceptionType exceptionType;

    //CONSTRUCTOR
    public StatesCensusAnalyserException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    //ENUM CLASS
    public enum ExceptionType {
        FILE_NOT_FOUND, INCORRECT_FILE_TYPE, NO_CENSUS_DATA, DELIMITER_AND_HEADER_INCORRECT
    }
}
