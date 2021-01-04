package com.example.ndif_yemmanuel.trackpay;

/**
 * Created by NDIF_YEMMANUEL on 10/28/2020.
 */

public class ItemRow {
    private String amount;
    private String date;
    private String type;
    private String status;

    public ItemRow(String amount, String date, String type, String status) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
