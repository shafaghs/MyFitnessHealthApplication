package com.shafagh.myfitnesshealthapplication.serachItem;


public class ItemInfo {

    private String itemName,itemUnit,itemCalories;

    public ItemInfo(String itemName, String itemUnit,String itemCalories) {
        this.itemCalories = itemCalories;
        this.itemName = itemName;
        this.itemUnit = itemUnit;
    }

    public String getItemCalories() {
        return itemCalories;
    }

    public void setItemCalories(String itemCalories) {
        this.itemCalories = itemCalories;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }


}
