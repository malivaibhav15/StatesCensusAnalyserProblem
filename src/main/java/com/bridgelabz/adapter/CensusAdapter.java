package com.bridgeLabzs.adapter;

import com.bridgeLabzs.DAO.CensusDAO;
import com.bridgeLabzs.dto.CSVStatesCensus;
import com.bridgeLabzs.dto.CSVUSCensus;
import com.bridgeLabzs.exception.StatesCensusAnalyserException;
import com.bridgeLabzs.services.CSVBuilderFactory;
import com.bridgeLabzs.services.OpenCSVBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {
    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StatesCensusAnalyserException;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCSVClass, String csvFilePath) throws StatesCensusAnalyserException {
        Map<String, CensusDAO> censusDAOMap = new HashMap<>();
        String extension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!extension.equals("csv")) {
            throw new StatesCensusAnalyserException("Incorrect file type", StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<E> stateCensusIterator = csvBuilder.getIterator(reader, censusCSVClass);
            Iterable<E> stateCensuses = () -> stateCensusIterator;
            if (censusCSVClass.getName().equals("com.bridgelabz.dto.CSVStateCensus")) {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(CSVStatesCensus.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(CSVStatesCensus.State, new CensusDAO(censusCSV)));
            } else if (censusCSVClass.getName().equals("com.bridgelabz.dto.CSVUSCensus")) {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(CSVUSCensus.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.State, new CensusDAO(censusCSV)));
            }
        } catch (RuntimeException e) {
            throw new StatesCensusAnalyserException("Incorrect delimiter or header", StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        } catch (FileNotFoundException e) {
            throw new StatesCensusAnalyserException("No such file", StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return censusDAOMap;
    }
}