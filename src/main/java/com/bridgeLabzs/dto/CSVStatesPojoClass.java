package com.bridgeLabzs.dto;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;
    @CsvBindByName(column = "SrNo", required = true)
    public int SrNo;
    @CsvBindByName(column = "StateName", required = true)
    public String StateName;
    @CsvBindByName(column = "TIN", required = true)
    public int TIN;


    public CSVStateCode() {
    }

    public CSVStateCode(int srNo, String stateName, int TIN, String stateCode) {
        SrNo = srNo;
        StateName = stateName;
        this.TIN = TIN;
        StateCode = stateCode;
    }

    @Override
    public String toString() {
        return "CSVStateCode{" +
                "StateCode=" + StateCode +
                ", SrNo=" + SrNo +
                ", StateName='" + StateName + '\'' +
                ", TIN=" + TIN +
                '}';
    }
}
