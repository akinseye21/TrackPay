package com.example.ndif_yemmanuel.trackpay;

import java.util.ArrayList;

/**
 * Created by NDIF_YEMMANUEL on 10/28/2020.
 */

public class GroupRow {
    private String rl_num;
    private String description;
    private ArrayList<ItemRow> childList;

    public GroupRow(String rl_num, String description, ArrayList<ItemRow> childList) {
        this.rl_num = rl_num;
        this.description = description;
        this.childList = childList;
    }

    public String getRl_num() {
        return rl_num;
    }

    public void setRl_num(String rl_num) {
        this.rl_num = rl_num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ItemRow> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<ItemRow> childList) {
        this.childList = childList;
    }
}
