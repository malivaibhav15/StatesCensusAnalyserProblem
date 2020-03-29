package com.bridgeLabzs.services;

import com.bridgeLabzs.model.CSVStateCensus;

public class CensusDAO {
    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;

    public CensusDAO(CSVStateCensus csvStatesCensus) {
        this.state = CSVStateCensus.State;
        this.population = csvStatesCensus.Population;
        this.area = csvStatesCensus.AreaInSqKm;
        this.density = csvStatesCensus.DensityPerSqKm;
    }

}

