package com.example.s528116.smartinventory;

import java.util.Date;

public class SupplyHistory {

    private int image;
    private String status;
    private String itemName;
    private String unitPrice;
    private String numberOfUnits;
    private Date requestCreatedDate;

    public SupplyHistory(int image, String status, String itemName, String unitPrice, String numberOfUnits, Date requestCreatedDate) {
        this.image = image;
        this.status = status;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.numberOfUnits = numberOfUnits;
        this.requestCreatedDate = requestCreatedDate;
    }

    public int getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public String getItemName() {
        return itemName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getNumberOfUnits() {
        return numberOfUnits;
    }

    public Date getRequestCreatedDate() {
        return requestCreatedDate;
    }
}


