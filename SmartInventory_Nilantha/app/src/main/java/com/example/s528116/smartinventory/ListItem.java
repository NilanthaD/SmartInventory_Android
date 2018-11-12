package com.example.s528116.smartinventory;

public class ListItem {
    private int image;
    private String itemName;
    private String itemPrice;
    private int nuOfItems;

    public ListItem(int image, String itemName, String itemPrice, int nuOfItems) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.nuOfItems = nuOfItems;
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getNuOfItems() {
        return nuOfItems;
    }

    public int getImage() {
        return image;
    }
}
