package com.bridgeLabzs.services;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class TestCaseStateCensusAnalyser
{
    @Test
    public void givenStateCensusAnalyserFile_WhenTrue_NumberOfRecordShouldMatch() throws IOException {
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        int count = censusAnalyser.loadCensusCSVData();
        Assert.assertEquals(29, count);
    }

}
