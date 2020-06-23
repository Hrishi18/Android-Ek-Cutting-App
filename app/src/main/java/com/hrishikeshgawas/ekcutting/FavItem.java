package com.hrishikeshgawas.ekcutting;

public class FavItem {
    private String itemSName, itemAddress, itemDescription, itemCost, key_id, itemLink;

    public FavItem() {
    }

    public FavItem(String itemSName, String itemAddress, String itemDescription, String itemCost, String key_id, String itemLink) {
        this.itemSName = itemSName;
        this.itemAddress = itemAddress;
        this.itemDescription = itemDescription;
        this.itemCost = itemCost;
        this.key_id = key_id;
        this.itemLink = itemLink;


    }

    public String getItemSName() {
        return itemSName;
    }

    public void setItemSName(String itemSName) {
        this.itemSName = itemSName;
    }

    public String getItemAddress() {
        return itemAddress;
    }

    public void setItemAddress(String itemAddress) {
        this.itemAddress = itemAddress;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getLink() {
        return itemLink;
    }

    public void setLink(String itemLink) {
        this.itemLink = itemLink;
    }


}
