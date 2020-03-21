package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.CSVBuilderException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class StateCensusAnalyser<E> {
    public static String CSV_FILE_PATH;
    private final Class<E> csvClass;
    int count = 0;

    public StateCensusAnalyser(String filePath, Class<E> csvClass) {
        CSV_FILE_PATH = filePath;
        this.csvClass = csvClass;
    }

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
}