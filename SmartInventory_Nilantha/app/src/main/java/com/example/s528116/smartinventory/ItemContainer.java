package com.example.s528116.smartinventory;

public class ItemContainer {
    private int image;
    private String itemID;
    private String itemName;
    private String unitPrice;
    private String qntyNeeded;
    private String requiredBy;
    private String documentId;

    public ItemContainer(String documentId,int image, String itemID, String itemName, String unitPrice, String qntyNeeded, String requiredBy) {
        this.documentId = documentId;
        this.image = image;
        this.itemID = itemID;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.qntyNeeded = qntyNeeded;
        this.requiredBy = requiredBy;
    }

    public String getDocumentId() {return documentId; }

    public int getImage() {
        return image;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getQntyNeeded() {
        return qntyNeeded;
    }

    public String getRequiredBy() {return requiredBy;}
}
