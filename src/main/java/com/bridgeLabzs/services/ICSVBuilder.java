package com.bridgeLabzs.services;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    <E> Iterator getIterator(Reader reader, Class csvClass);
    <E> List getList(Reader reader, Class csvClass);
}
