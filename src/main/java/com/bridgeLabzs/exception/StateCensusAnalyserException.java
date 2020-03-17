package com.bridgeLabzs.exception;

public class StateCensusAnalyserException extends Exception {
    public enum ExceptionType {
        FILE_NOT_FOUND,
        DELIMITER_INCORRECT
    }
    public ExceptionType exceptionType;

    public StateCensusAnalyserException (String message, ExceptionType exceptionType)
    {
        super(message);
        this.exceptionType = exceptionType;
    }
}