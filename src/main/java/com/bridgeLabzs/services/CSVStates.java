package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.StateCensusAnalyserException;
import com.bridgeLabzs.model.CSVStateCode;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class CSVStates {
    public static String STATE_CSV_FILE_PATH;
    int count = 0;

    public CSVStates(String path) {
        STATE_CSV_FILE_PATH = path;
    }

    public int countCVSStateCode() throws StateCensusAnalyserException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_FILE_PATH))
        ) {
            CsvToBean<CSVStateCode> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStateCode> csvUserIterator = csvToBean.iterator();
            while ((csvUserIterator.hasNext())) {
                CSVStateCode csvStateCode = csvUserIterator.next();
                System.out.println("SrNo : " + csvStateCode.getSrNo());
                System.out.println("State Name : " + csvStateCode.getStateName());
                System.out.println("TIN : " + csvStateCode.getTIN());
                System.out.println("StateCode :" + csvStateCode.getStateCode());
                System.out.println("=========================");
                count++;
            }
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(e.getMessage(), StateCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(e.getMessage(), StateCensusAnalyserException.ExceptionType.DELIMITER_INCORRECT);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

}