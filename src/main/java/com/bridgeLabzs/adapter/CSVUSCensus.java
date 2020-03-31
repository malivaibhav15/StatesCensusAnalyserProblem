package com.bridgeLabzs.adapter;

import com.opencsv.bean.CsvBindByName;

public class CSVUSCensus
{
    @CsvBindByName(column = "State Id", required = true)
    public String StateID;

    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "Population Density", required = true)
    public String PopulationDensity;

    @CsvBindByName(column = "Population", required = true)
    public String Population;

    @CsvBindByName(column = "Total area", required = true)
    public String Area;

    @CsvBindByName(column = "Housing units", required = true)
    public String HousingUnits;

    @CsvBindByName(column = "Water area", required = true)
    public String WaterArea;

    @CsvBindByName(column = "Land Area", required = true)
    public String LandArea;

    @CsvBindByName(column = "Housing Density", required = true)
    public String HousingDensity;
}
