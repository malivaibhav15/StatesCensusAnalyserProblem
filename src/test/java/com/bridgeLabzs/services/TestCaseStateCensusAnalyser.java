package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.StatesCensusAnalyserException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class TestCaseStateCensusAnalyser {

    StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();

    @Test
    public void givenStateCensusAnalyserFile_WhenTrue_NumberOfRecordShouldMatch() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (StatesCensusAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenStateCensusAnalyserFile_WhenWrong_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StatesssssssCensusData.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenStateCensusAnalyserFileType_WhenWrong_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.txt";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenDelimiterIncorrect_ReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenHeaderIncorrect_ReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData2.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenStateCode_WhenProper_ShouldMatchCount() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadData(CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (StatesCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeFile_WhenIncorrect_ShouldGiveException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenStateCodeFileType_WhenIncorrect_ShouldGiveException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.txt";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenStateCodeFile_WithIncorrectDelimeter_ShouldGiveException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenStateCodeFile_WithIncorrectHeaderName_ShouldGiveException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode2.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenCensusData_WhenSorted_ShouldReturnSortedList() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
            String SortedData = stateCensusAnalyzer.SortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(SortedData, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
            String SortedData = stateCensusAnalyzer.SortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(SortedData, CensusDAO[].class);
            Assert.assertEquals("AD", censusCSV[0].stateCode);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }

    }
}