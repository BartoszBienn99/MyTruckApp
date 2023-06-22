package com.MyTruckApp.dto;

public class UpdateDriverDto {

    private int companyId;
    private int truckId;

    public UpdateDriverDto() {

    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }
}
