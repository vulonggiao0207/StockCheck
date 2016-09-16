package com.giao.Model;

/**
 * Created by Long on 9/16/2016.
 */
public class ItemCheck {
    private String CheckDate;
    private int ItemID;

    public ItemCheck() {
    }

    public ItemCheck(String checkDate, int itemID) {

        CheckDate = checkDate;
        ItemID = itemID;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String checkDate) {
        CheckDate = checkDate;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }
}
