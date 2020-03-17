    package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

public class TestCaseStateCensusAnalyser {
    String CSV_FILE_PATH;

    @Test
    public void givenStateCensusAnalyserFile_WhenTrue_NumberOfRecordShouldMatch() throws StateCensusAnalyserException {
        CSV_FILE_PATH = "/home/admin1/Desktop/JAVA/StateCensusAnalyser/src/test/resources/StateCensusData.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        int count = censusAnalyser.loadCensusCSVData(CSV_FILE_PATH);
        Assert.assertEquals(29, count);
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() {
        CSV_FILE_PATH = "/home/admin1/Desktop/JAVA/StateCensusAnalyser/src/test/resources/StateCensusData.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        try {
            censusAnalyser.loadCensusCSVData(CSV_FILE_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INPUT_OUTPUT_OPERATION_FALIED, e.type);
        }
    }
}

