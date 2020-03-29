package com.bridgeLabzs.model;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    public static String State;
    @CsvBindByName(column = "Population", required = true)
    public int Population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    public int AreaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int DensityPerSqKm;
}