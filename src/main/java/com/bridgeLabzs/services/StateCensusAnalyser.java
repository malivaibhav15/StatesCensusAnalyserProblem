package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.StateCensusAnalyserException;
import com.bridgeLabzs.model.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    public static String CSV_FILE_PATH;
    int count = 1;

    public StateCensusAnalyser(String filePath) {
        CSV_FILE_PATH = filePath;
    }

    public int loadCensusCSVData() throws StateCensusAnalyserException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))
        ) {
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStateCensus> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                CSVStateCensus csvStateCensus = csvUserIterator.next();
                System.out.println("Sr no. :" + count);
                System.out.println("State: " + csvStateCensus.getState());
                System.out.println("Population: " + csvStateCensus.getPopulation());
                System.out.println("Area: " + csvStateCensus.getAreaInSqKm());
                System.out.println("Density: " + csvStateCensus.getDensityPerSqKm());
                System.out.println("=============================");
                count++;
            }
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(e.getMessage(), StateCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }catch (RuntimeException e) {
            throw new StateCensusAnalyserException(e.getMessage(), StateCensusAnalyserException.ExceptionType.DELIMITER_INCORRECT);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}