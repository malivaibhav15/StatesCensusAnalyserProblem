package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.CSVBuilderException;
import com.bridgeLabzs.model.CSVStateCensus;
import com.bridgeLabzs.model.CSVStateCode;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class StateCensusAnalyser<E> {
    public static String CSV_FILE_PATH;
    List<CSVStateCensus> csvStatesCensusList = null;
    List<CSVStateCode> csvStatesPojoClassList = null;
    Map<String, CSVStateCensus> csvStatesCensusMap = null;
    Map<String, CSVStateCode> csvStatesPojoClassMap = null;

    public StateCensusAnalyser() {
        this.csvStatesCensusMap = new HashMap<>();
        this.csvStatesPojoClassMap = new HashMap<>();
    }

    //METHOD TO LOAD RECORDS
    public int loadCensusCSVData(String path) throws CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<CSVStateCensus> csvStatesCensusIterator = csvBuilder.getIterator(reader, CSVStateCensus.class);
            while (csvStatesCensusIterator.hasNext()) {
                CSVStateCensus csvStatesCensus = csvStatesCensusIterator.next();
                this.csvStatesCensusMap.put(CSVStateCensus.State, csvStatesCensus);
                csvStatesCensusList = csvStatesCensusMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = csvStatesCensusMap.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException  e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.DELIMITER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int loadData(String path) throws CSVBuilderException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path))
        ) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<CSVStateCode> csvStatesCensusIterator = csvBuilder.getIterator(reader, CSVStateCensus.class);
            while (csvStatesCensusIterator.hasNext()) {
                CSVStateCode csvStatesPojoClass = csvStatesCensusIterator.next();
                this.csvStatesPojoClassMap.put(CSVStateCode.StateCode, csvStatesPojoClass);
                csvStatesPojoClassList = csvStatesPojoClassMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = csvStatesPojoClassMap.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("Give proper file name and path", CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Check delimiters and headers", CSVBuilderException.ExceptionType.DELIMITER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String SortedCensusData() throws CSVBuilderException {
        if (csvStatesCensusList == null || csvStatesCensusList.size() == 0) {
            throw new CSVBuilderException("No census data", CSVBuilderException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensus> comparator = Comparator.comparing(csvStatesCensus -> csvStatesCensus.State);
        this.sortData(comparator, csvStatesCensusList);
        String sortedStateCensusJson = new Gson().toJson(csvStatesCensusList);
        return sortedStateCensusJson;
    }

    public String SortedStateCodeData() throws CSVBuilderException {
        if (csvStatesPojoClassList == null || csvStatesPojoClassList.size() == 0) {
            throw new CSVBuilderException("No census data", CSVBuilderException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCode> comparator = Comparator.comparing(csvStatesPojoClass -> csvStatesPojoClass.StateCode);
        this.sortData(comparator, csvStatesPojoClassList);
        String sortedStateCodeJson = new Gson().toJson(csvStatesPojoClassList);
        return sortedStateCodeJson;
    }

    private <E> void sortData(Comparator<E> csvComparator, List<E> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                E census1 = list.get(j);
                E census2 = list.get(j + 1);
                list.set(j, census2);
                list.set(j + 1, census1);
            }
        }
    }
}