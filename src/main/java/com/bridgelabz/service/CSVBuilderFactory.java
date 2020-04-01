package com.bridgelabz.service;

public class CSVBuilderFactory {
    public static OpenCSV getCensusData() {
        return new OpenCSV();
    }
}