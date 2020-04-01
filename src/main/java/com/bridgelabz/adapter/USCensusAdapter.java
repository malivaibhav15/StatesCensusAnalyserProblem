package com.bridgeLabzs.adapter;

import com.bridgeLabzs.DAO.CensusDAO;
import com.bridgeLabzs.dto.CSVUSCensus;
import com.bridgeLabzs.exception.StatesCensusAnalyserException;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StatesCensusAnalyserException {
        return super.loadCensusData(CSVUSCensus.class, csvFilePath[0]);
    }
}
