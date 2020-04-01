package com.bridgelabz.services;

public class CSVBuilderFactory {
    public static OpenCSVBuilder createCsvBuilder() {
        return new OpenCSVBuilder();
    }
}
