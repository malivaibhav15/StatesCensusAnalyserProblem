package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestCaseStateCensusAnalyser {

    @Test
    public void givenStateCensusAnalyserFile_WhenTrue_NumberOfRecordShouldMatch() throws StateCensusAnalyserException {
        final String CSV_FILE_PATH = "/home/admin1/Desktop/JAVA/StateCensusAnalyser/src/test/resources/StateCensusData.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH);
        int count = censusAnalyser.loadCensusCSVData();
        Assert.assertEquals(29, count);
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenMatched_ReturnsException() {
        final String CSV_FILE_PATH = "/home/admin1/Desktop/JAVA/StateCensusAnalyser/src/resources/StateCensusData.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH);
        try {
            censusAnalyser.loadCensusCSVData();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenWrong_ReturnsException() {
        final String CSV_FILE_PATH = "/home/admin1/Desktop/JAVA/StateCensusAnalyser/src/test/resources/StateCensusData.txt";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH);
        try {
            censusAnalyser.loadCensusCSVData();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenDelimiterIncorrect_ReturnCustomiseException() {
        final String CSV_FILE_PATH = "/home/admin1/Desktop/JAVA/StateCensusAnalyserProblem/src/test/resources/StateCensusData1.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH);
        try {
            censusAnalyser.loadCensusCSVData();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.DELIMITER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenHeaderIncorrect_ReturnCustomiseException() {
        final String CSV_FILE_PATH = "/home/admin1/Desktop/JAVA/StateCensusAnalyser/src/test/resources/StateCensusData.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH);
        try {
            censusAnalyser.loadCensusCSVData();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.DELIMITER_INCORRECT, e.exceptionType);
        }
    }
    @Test
    public void givenStateCode_WhenProper_ShouldMatchCount() throws IOException {
        String STATE_CSV_FILE_PATH = "/home/admin1/Desktop/JAVA/StateCensusAnalyserProblem/src/test/resources/StateCode.csv";
        CSVStates csvStates = new CSVStates(STATE_CSV_FILE_PATH);
        int count = csvStates.countCVSStateCode();
        Assert.assertEquals(37, count);
    }
}

