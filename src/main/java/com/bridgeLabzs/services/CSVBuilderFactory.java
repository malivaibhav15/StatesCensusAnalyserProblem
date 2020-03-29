package com.bridgeLabzs.services;

public class CSVBuilderFactory {
    public static OpenCSVBuilder createCsvBuilder() {
        return new OpenCSVBuilder();
    }
}
