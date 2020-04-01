package com.bridgelabz.service;

import com.bridgelabz.exception.StatesCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSV implements CSV_Interface {
    //    ITERATOR OF CSV FILE
    @Override
    public <E> Iterator<E> getIterator(Reader reader, Class csvClass) throws StatesCensusAnalyserException {
        try {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new StatesCensusAnalyserException("Unable to parse",  StatesCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    //    LIST OF CSV FILE
    @Override
    public <E> List < E > getList(Reader reader, Class csvClass) throws StatesCensusAnalyserException {
        try {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (IllegalStateException e) {
            throw new StatesCensusAnalyserException("Unable to parse",  StatesCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}