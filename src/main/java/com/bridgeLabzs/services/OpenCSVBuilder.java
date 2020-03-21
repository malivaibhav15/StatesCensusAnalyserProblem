package com.bridgeLabzs.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder {
    //Iterator for csv file
    @Override
    public <E> Iterator getIterator(Reader reader, Class csvClass) {
        CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<E> csvUserIterator = csvToBean.iterator();
        return csvUserIterator;
    }

    //LIST OF CSV FILE
    @Override
    public <E> List getList(Reader reader, Class csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true);
        return csvToBeanBuilder.build().parse();
    }
}
