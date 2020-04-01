package com.bridgelabz.service;

import com.bridgelabz.dto.CSVStatesCensus;
import com.bridgelabz.dto.CSVStatesPojoClass;
import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class TestCaseCensusAnalyser {

    CensusAnalyser stateCensusAnalyzer = new CensusAnalyser();

    @Test
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (StatesCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StatesssssssCensusData.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileType_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.txt";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenHeaderIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData2.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenNumberOfRecordsOfStateCode_WhenMatched_ShouldReturnTrue() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (StatesCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileNameOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFileTypeOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.txt";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileOfStateCode_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFileOfStateCode_WhenHeadersIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode2.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (StatesCensusAnalyserException e) {
            Assert.assertEquals(StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenCensusData_WhenSorted_ShouldReturnSortedList() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedData = stateCensusAnalyzer.SortedStateCensusData();
            CSVStatesCensus[] censusCSV = new Gson().fromJson(sortedData, CSVStatesCensus[].class);
            Assert.assertEquals("Andhra Pradesh", CSVStatesCensus.state);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String SortedData = stateCensusAnalyzer.SortedStateCensusData();
            CSVStatesPojoClass[] stateCodes = new Gson().fromJson(SortedData, CSVStatesPojoClass[].class);
            Assert.assertEquals("AD", stateCodes[0].StateCode);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getPopulationWiseSortedCensusData();
            CSVStatesCensus[] csvStatesCensus = new Gson().fromJson(sortedCensusData, CSVStatesCensus[].class);
            Assert.assertEquals(199812341, csvStatesCensus[0].Population);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getDensityWiseSortedCensusData();
            CSVStatesCensus[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CSVStatesCensus[].class);
            Assert.assertEquals(1102, csvStateCensuses[0].DensityPerSqkm);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnAreaInPerSqKm_ShouldReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.getAreaWiseSortedCensusData();
            CSVStatesCensus[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CSVStatesCensus[].class);
            Assert.assertEquals(342239, csvStateCensuses[0].AreaInSqKm);
        } catch (StatesCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUSCensusAnalyserFile_WhenTrue_NumberOfRecordShouldMatch() throws StatesCensusAnalyserException {
        final String CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
        int count = stateCensusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.US, CSV_FILE_PATH);
        Assert.assertEquals(51, count);
    }
}