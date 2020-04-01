package com.bridgelabz.services;

import com.bridgelabz.dao.CensusDAO;
import com.bridgelabz.adapter.CensusAdapterFactory;
import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.newBufferedReader;

public class CensusAnalyser<E> {
    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusDAOMap = null;

    //CONSTRUCTOR
    public CensusAnalyser() {
        this.censusDAOMap = new HashMap<>();
        this.censusList = new ArrayList<>();
    }
    public int loadStateCensusCSVData(Country country, String... csvFilePath) throws StatesCensusAnalyserException {
        censusDAOMap =  CensusAdapterFactory.getCensusData(country, csvFilePath);
        censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        return censusDAOMap.size();
    }

        //METHOD TO SORT STATE CENSUS DATA
    public String SortedStateCensusData() throws StatesCensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new StatesCensusAnalyserException("No census data", StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> comparator = Comparator.comparing(censusDAO -> censusDAO.State);
        this.sortData(comparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationWiseSortedCensusData() throws StatesCensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new StatesCensusAnalyserException("No census data", StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.Population);
        this.sortData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT DATA DENSITYWISE
    public String getDensityWiseSortedCensusData() throws StatesCensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new StatesCensusAnalyserException("No census data", StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.DensityPerSqkm);
        this.sortData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY AREA
    public String getAreaWiseSortedCensusData() throws StatesCensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new StatesCensusAnalyserException("No census data", StatesCensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.AreaInSqKm);
        this.sortData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;

    }

    //METHOD TO SORT DATA
    private void sortData(Comparator<CensusDAO> csvComparator) {
        {
            for (int i = 0; i < censusList.size() - 1; i++) {
                for (int j = 0; j < censusList.size() - i - 1; j++) {
                    CensusDAO census1 = censusList.get(j);
                    CensusDAO census2 = censusList.get(j + 1);
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }
            }
        }
    }
    public enum Country {INDIA, US}
}