package com.example.s528116.smartinventory;

import java.util.Date;

public class SupplyHistory {

    private int image;
    private String status;
    private String itemName;
    private String unitPrice;
    private String numberOfUnits;
    private Date requestCreatedDate;
    private String userEmail;
    private String message;
    private String totalValue;
    private String paymentStatus;
    private String itemDocId;

    public SupplyHistory(String userEmail, int image, String status, String itemName, String unitPrice, String numberOfUnits, Date requestCreatedDate
    , String message, String totalValue, String paymentStatus, String itemDocId) {
        this.image = image;
        this.status = status;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.numberOfUnits = numberOfUnits;
        this.requestCreatedDate = requestCreatedDate;
        this.userEmail = userEmail;
        this.message = message;
        this.totalValue = totalValue;
        this.paymentStatus = paymentStatus;
        this.itemDocId = itemDocId;
    }

    public String getUserEmail(){return userEmail;}

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

    public String getMessage() {
        return message;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getItemDocId() {
        return itemDocId;
    }
}


