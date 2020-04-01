package com.bridgelabz.adapter;

import com.bridgelabz.dao.CensusDAO;
import com.bridgelabz.dto.CSVStatesCensus;
import com.bridgelabz.dto.CSVStatesPojoClass;
import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.bridgelabz.service.CSVBuilderFactory;
import com.bridgelabz.service.OpenCSV;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndianCensusAdapter  extends CensusAdapter{
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StatesCensusAnalyserException {
        Map<String, CensusDAO> censusDAOMap = super.loadCensusData(CSVStatesCensus.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusDAOMap;
        return loadStateCodeCSVData(censusDAOMap, csvFilePath[1]);
    }

    private Map<String, CensusDAO> loadStateCodeCSVData(Map<String, CensusDAO> censusDAOMap, String csvFilePath) throws StatesCensusAnalyserException {
        String extension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!extension.equals("csv")) {
            throw new StatesCensusAnalyserException("Incorrect file type", StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            OpenCSV csvBuilder = CSVBuilderFactory.getCensusData();
            Iterator<CSVStatesPojoClass> stateCodeIterator = csvBuilder.getIterator(reader, CSVStatesPojoClass.class);
            Iterable<CSVStatesPojoClass> stateCodes = () -> stateCodeIterator;
            StreamSupport.stream(stateCodes.spliterator(), false)
                    .filter(csvStatesPojoClass -> censusDAOMap.get(csvStatesPojoClass.StateName) != null)
                    .forEach(csvStatesPojoClass -> censusDAOMap.get(csvStatesPojoClass.StateName).StateCode = csvStatesPojoClass.StateCode);
        } catch (RuntimeException e) {
            throw new StatesCensusAnalyserException("Incorrect delimiter or header in file", StatesCensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_INCORRECT);
        } catch (FileNotFoundException e) {
            throw new StatesCensusAnalyserException( "No such file", StatesCensusAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return censusDAOMap;
    }
}