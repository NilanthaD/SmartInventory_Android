package com.example.adminsmartinventory;

import java.net.URI;
import java.util.Date;

public class ItemViewContainer {

    private String image;
    private String itemID;
    private String itemName;
    private long unitPrice;
    private long qntyNeeded;
    private Date requiredBy;
    private String documentId;
    private String userEmail;

    public ItemViewContainer(String userEmail, String documentId,String image, String itemID, String itemName, Long unitPrice, long qntyNeeded, Date requiredBy) {
        this.userEmail = userEmail;
        this.documentId = documentId;
        this.image = image;
        this.itemID = itemID;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.qntyNeeded = qntyNeeded;
        this.requiredBy = requiredBy;
    }

    public String getUserEmail() {return userEmail;}

    public String getDocumentId() {return documentId; }

    public String  getImage() {
        return image;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public long getQntyNeeded() {
        return qntyNeeded;
    }

    public Date getRequiredBy() {return requiredBy;}
}
