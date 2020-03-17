package com.bridgeLabzs.services;
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
    private static final String CSV_FILE_PATH = "/home/admin1/Desktop/JAVA/StateCensusAnalyser/src/test/resources/StateCensusData.csv";
    int count=1;
    public int loadCensusCSVData() throws IOException
    {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
        ) {
            CsvToBean<CSVStateCensus> csvStateCensuses = new CsvToBeanBuilder(reader)
            .withType(CSVStateCensus.class)
            .withIgnoreLeadingWhiteSpace(true)
            .build();

            Iterator<CSVStateCensus> csvStateCensusIterator = csvStateCensuses.iterator();
            while (csvStateCensusIterator.hasNext())
            {
                CSVStateCensus csvStateCensus = csvStateCensusIterator.next();
                System.out.println("State: " + csvStateCensus.getState());
                System.out.println("Population: " + csvStateCensus.getPopulation());
                System.out.println("Area: " + csvStateCensus.getAreaInSqKm());
                System.out.println("Density: " + csvStateCensus.getDensityPerSqKm());
                System.out.println("=============================");
                System.out.println("Count :" +count);
                count++;
            }
        }
        return count;
    }
}