package com.bridgeLabzs.DAO;

import com.bridgeLabzs.model.CSVStateCensus;
import com.bridgeLabzs.model.CSVStateCode;

public class CensusDAO {
    public String State;
    public long Population;
    public long AreaInSqKm;
    public int DensityPerSqkm;
    public String StateCode;
    public int TIN;
    public int SrNo;

    public CensusDAO(CSVStateCensus csvStatesCensus) {
        this.State = csvStatesCensus.State;
        this.Population = csvStatesCensus.Population;
        this.AreaInSqKm = csvStatesCensus.AreaInSqKm;
        this.DensityPerSqkm = csvStatesCensus.DensityPerSqKm;
    }

    public CensusDAO(CSVStateCode csvStatesPojoClass){
        this.State = csvStatesPojoClass.StateName;
        this.SrNo = csvStatesPojoClass.SrNo;
        this.TIN = csvStatesPojoClass.TIN;
        this.StateCode = csvStatesPojoClass.StateCode;

    }
}