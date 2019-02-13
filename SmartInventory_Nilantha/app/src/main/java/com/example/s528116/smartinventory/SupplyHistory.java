package com.example.s528116.smartinventory;

public class SupplyHistory {

    private int image;
    private String status;
    private String itemName;
    private String unitPrice;
    private String numberOfUnits;
    private String requestCreatedDate;

    public SupplyHistory(int image, String status, String itemName, String unitPrice, String numberOfUnits, String requestCreatedDate) {
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

    public String getRequestCreatedDate() {
        return requestCreatedDate;
    }
}


