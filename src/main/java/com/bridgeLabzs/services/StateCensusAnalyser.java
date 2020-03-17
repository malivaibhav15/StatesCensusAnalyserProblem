package com.bridgeLabzs.services;
import com.bridgeLabzs.exception.StateCensusAnalyserException;
import com.bridgeLabzs.model.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser
{
    int count=1;
    public int loadCensusCSVData(String path) throws StateCensusAnalyserException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
        ) {
            CsvToBean<CSVStateCensus> csvStateCensuses = new CsvToBeanBuilder(reader)
            .withType(CSVStateCensus.class)
            .withIgnoreLeadingWhiteSpace(true)
            .build();

            Iterator<CSVStateCensus> csvStateCensusIterator = csvStateCensuses.iterator();
            while (csvStateCensusIterator.hasNext())
            {
                CSVStateCensus csvStateCensus = csvStateCensusIterator.next();
                System.out.println("Sr no. :" +count);
                System.out.println("State: " + csvStateCensus.getState());
                System.out.println("Population: " + csvStateCensus.getPopulation());
                System.out.println("Area: " + csvStateCensus.getAreaInSqKm());
                System.out.println("Density: " + csvStateCensus.getDensityPerSqKm());
                System.out.println("=============================");
                count++;
            }
        }catch (IOException e) {
            throw new StateCensusAnalyserException(e.getMessage(),StateCensusAnalyserException.ExceptionType.INPUT_OUTPUT_OPERATION_FALIED);
        }
        return count;
    }
}