package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.CSVBuilderException;
import com.bridgeLabzs.model.CSVStateCensus;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class StateCensusAnalyser<E> {
    public static String CSV_FILE_PATH;
    private final Class<E> csvClass;
    List<E> csvUserList = null;

    public StateCensusAnalyser(String filePath, Class<E> csvClass) {
        CSV_FILE_PATH = filePath;
        this.csvClass = csvClass;
    }

    //METHOD TO LOAD RECORDS
    public int loadCensusCSVData() throws CSVBuilderException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))
        ) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            List<E> csvUserList = csvBuilder.getList(reader, csvClass);
            return csvUserList.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.DELIMITER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getSortedCensusData() {
        Comparator<CSVStateCensus> CSVComparator = Comparator.comparing(census -> census.State);
        this.sort((Comparator<E>) CSVComparator);
        String SortedCSVJson = new Gson().toJson(csvUserList);
        return SortedCSVJson;
    }
    private void sort(Comparator<E> csvComparator) {
        for (int i = 0; i < csvUserList.size() - 1; i++) {
            for (int j = 0; j < csvUserList.size() - i - 1; j++) {
                E census1 = csvUserList.get(j);
                E census2 = csvUserList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    csvUserList.set(j, census2);
                    csvUserList.set(j + 1, census1);
                }
            }
        }
    }
}