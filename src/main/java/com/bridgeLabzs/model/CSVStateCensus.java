package com.bridgeLabzs.model;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus
{
    @CsvBindByName(column = "State")
    private String State;
    @CsvBindByName(column = "Population")
    private String Population;
    @CsvBindByName(column = "AreaInSqKm")
    private String AreaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm")
    private String DensityPerSqKm;

    public String getState() {
        return State;
    }

    public String getPopulation() {
        return Population;
    }

    public String getAreaInSqKm() {
        return AreaInSqKm;
    }

    public String getDensityPerSqKm() {
        return DensityPerSqKm;
    }

    public void setState(String state) {
        State = state;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        DensityPerSqKm = densityPerSqKm;
    }
}