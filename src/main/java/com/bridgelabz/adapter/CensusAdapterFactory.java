package com.bridgelabz.adapter;

import com.bridgelabz.dao.CensusDAO;
import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.service.CensusAnalyser;

import java.util.Map;

public class CensusAdapterFactory {
    public static Map<String, CensusDAO> getCensusData(CensusAnalyser.Country country, String[] csvFilePath) throws StatesCensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new IndianCensusAdapter().loadCensusData(csvFilePath);
        else if (country.equals(CensusAnalyser.Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath);
        else
            throw new StatesCensusAnalyserException( "Invalid country", StatesCensusAnalyserException.ExceptionType.INVALID_COUNTRY);
    }
}