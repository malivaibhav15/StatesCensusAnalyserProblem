package com.bridgeLabzs.services;

import com.bridgeLabzs.exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface CSV_Interface {
    <E> Iterator<E> getIterator(Reader reader, Class csvClass) throws CSVBuilderException;

    <E> List<E> getList(Reader reader, Class csvClass) throws CSVBuilderException;
}
