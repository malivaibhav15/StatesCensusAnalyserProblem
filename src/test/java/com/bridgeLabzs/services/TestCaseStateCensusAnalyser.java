package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.CSVBuilderException;
import com.bridgeLabzs.model.CSVStateCensus;
import com.bridgeLabzs.model.CSVStateCode;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class TestCaseStateCensusAnalyser {

    @Test
    public void givenStateCensusAnalyserFile_WhenTrue_NumberOfRecordShouldMatch() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
        int count = censusAnalyser.loadCensusCSVData();
        Assert.assertEquals(29, count);
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenMatched_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StatesssssssCensusData.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
        try {
            censusAnalyser.loadCensusCSVData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenWrong_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.txt";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
        try {
            censusAnalyser.loadCensusCSVData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenDelimiterIncorrect_ReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
        try {
            censusAnalyser.loadCensusCSVData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenHeaderIncorrect_ReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
        try {
            censusAnalyser.loadCensusCSVData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenStateCode_WhenProper_ShouldMatchCount() throws CSVBuilderException {
        String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCode.class);
        int count = stateCensusAnalyser.loadCensusCSVData();
        Assert.assertEquals(37, count);
    }

    @Test
    public void givenStateCodeFile_WhenIncorrect_ShouldGiveException() {
        String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCode.class);
        try {
            stateCensusAnalyser.loadCensusCSVData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenStateCodeFileType_WhenIncorrect_ShouldGiveException() {
        String CSV_FILE_PATH = "src/test/resources/StateCode.txt";
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCode.class);
        try {
            stateCensusAnalyser.loadCensusCSVData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenStateCodeFile_WithIncorrectDelimeter_ShouldGiveException() {
        String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCode.class);
        try {
            stateCensusAnalyser.loadCensusCSVData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenStateCodeFile_WithIncorrectHeaderName_ShouldGiveException() {
        String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCode.class);
        try {
            stateCensusAnalyser.loadCensusCSVData();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_INCORRECT, e.exceptionType);
        }
    }
    @Test
    public void givenCensusData_WhenSorted_ShouldReturnSortedList() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
        stateCensusAnalyzer.loadCensusCSVData();
        String SortedData = stateCensusAnalyzer.getSortedCensusData();
        CSVStateCensus[] censusCSV = new Gson().fromJson(SortedData, CSVStateCensus[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
    }
}
