package com.bridgeLabzs.services;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder
{
    public <E> Iterator getIterator(Reader reader,Class csvClass );
}
