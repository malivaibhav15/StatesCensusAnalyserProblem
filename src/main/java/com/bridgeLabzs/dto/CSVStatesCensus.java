package com.bridgeLabzs.dto;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    public static String State;
    @CsvBindByName(column = "Population", required = true)
    public long Population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    public long AreaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int DensityPerSqKm;

    public CSVStateCensus(long population, long areaInSqKm, int densityPerSqkm) {
        Population = population;
        AreaInSqKm = areaInSqKm;
        DensityPerSqKm = densityPerSqkm;
    }

    @Override
    public String toString() {
        return "CSVStatesCensus{" +
                "Population=" + Population +
                ", AreaInSqKm=" + AreaInSqKm +
                ", DensityPerSqKm=" + DensityPerSqKm +
                '}';
    }
}