package com.bridgeLabzs.model;

import com.opencsv.bean.CsvBindByName;

    public class CSVStateCode {
    @CsvBindByName(column = "SrNo", required = true)
    private String SrNo;
    @CsvBindByName(column = "StateName", required = true)
    private String StateName;
    @CsvBindByName(column = "TIN", required = true)
    private String TIN;
    @CsvBindByName(column = "StateCode", required = true)
    private String StateCode;


    public CSVStateCode() {
    }
    public CSVStateCode(String srNo, String stateName, String TIN, String stateCode) {
        SrNo = srNo;
        StateName = stateName;
        this.TIN = TIN;
        StateCode = stateCode;
    }

    public String getSrNo()
    { return SrNo;
    }
    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getTIN() {
        return TIN;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }
}
