package com.bridgeLabzs.exception;

public class CSVBuilderException extends Exception {public enum ExceptionType {
    FILE_NOT_FOUND,
    DELIMITER_INCORRECT
}
    public StateCensusAnalyserException.ExceptionType exceptionType;

    public CSVBuilderException (String message, StateCensusAnalyserException.ExceptionType exceptionType)
    {
        super(message);
        this.exceptionType = exceptionType;
    }}
