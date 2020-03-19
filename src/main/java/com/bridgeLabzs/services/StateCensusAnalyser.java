package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.StateCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser<E> {
    public static String CSV_FILE_PATH;
    private final Class<E> csvClass;
    int count = 0;

    public StateCensusAnalyser(String filePath, Class<E> csvClass) {
        CSV_FILE_PATH = filePath;
        this.csvClass = csvClass;
    }

    public <E> int loadCensusCSVData() throws StateCensusAnalyserException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))
        ) {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<E> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                csvUserIterator.next();
                count++;
            }
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(e.getMessage(),
                    StateCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(e.getMessage(),
                    StateCensusAnalyserException.ExceptionType.DELIMITER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}