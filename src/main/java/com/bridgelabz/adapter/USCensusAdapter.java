package com.bridgelabz.adapter;

import com.bridgelabz.dao.CensusDAO;
import com.bridgelabz.dto.CSVUSCensus;
import com.bridgelabz.exception.StatesCensusAnalyserException;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StatesCensusAnalyserException {
        return super.loadCensusData(CSVUSCensus.class, csvFilePath[0]);
    }
}