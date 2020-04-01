package com.bridgeLabzs.DAO;

import com.bridgeLabzs.dto.CSVStatesCensus;
import com.bridgeLabzs.dto.CSVStatesPojoClass;
import com.bridgeLabzs.dto.CSVUSCensus;

public class CensusDAO {
    public float HousingDensity;
    public String StateID;
    public String State;
    public long Population;
    public long AreaInSqKm;
    public int DensityPerSqkm;
    public String StateCode;
    public int TIN;
    public int SrNo;

    public CensusDAO(CSVStatesCensus csvStatesCensus) {
        this.State = CSVStatesCensus.State;
        this.Population = csvStatesCensus.Population;
        this.AreaInSqKm = csvStatesCensus.AreaInSqKm;
        this.DensityPerSqkm = csvStatesCensus.DensityPerSqKm;
    }

    public CensusDAO(CSVStatesPojoClass csvStateCensus) {
        this.State = csvStateCensus.StateName;
        this.SrNo = csvStateCensus.SrNo;
        this.TIN = csvStateCensus.TIN;
        this.StateCode = csvStateCensus.StateCode;

    }

    public CensusDAO(CSVUSCensus csvusCensus) {
        this.StateID = csvusCensus.StateID;
        this.State = csvusCensus.State;
        this.Population = csvusCensus.Population;
        this.AreaInSqKm = csvusCensus.Area;
        this.DensityPerSqkm = csvusCensus.PopulationDensity;
        this.HousingDensity = csvusCensus.HousingDensity;
    }
}