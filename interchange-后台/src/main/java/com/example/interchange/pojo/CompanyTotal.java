package com.example.interchange.pojo;

/**
 * @author 秃头小刘
 * @date 2022-05-06 9:27 PM
 */


public class CompanyTotal {
    String id;
    String tt_count;
    String tt_height;
    String tt_peak;
    String tt_trade;

    public CompanyTotal() {
    }

    @Override
    public String toString() {
        return "CompanyTotal{" +
                "id='" + id + '\'' +
                ", tt_count='" + tt_count + '\'' +
                ", tt_height='" + tt_height + '\'' +
                ", tt_peak='" + tt_peak + '\'' +
                ", tt_trade='" + tt_trade + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTt_count() {
        return tt_count;
    }

    public void setTt_count(String tt_count) {
        this.tt_count = tt_count;
    }

    public String getTt_height() {
        return tt_height;
    }

    public void setTt_height(String tt_height) {
        this.tt_height = tt_height;
    }

    public String getTt_peak() {
        return tt_peak;
    }

    public void setTt_peak(String tt_peak) {
        this.tt_peak = tt_peak;
    }

    public String getTt_trade() {
        return tt_trade;
    }

    public void setTt_trade(String tt_trade) {
        this.tt_trade = tt_trade;
    }

    public CompanyTotal(String id, String tt_count, String tt_height, String tt_peak, String tt_trade) {
        this.id = id;
        this.tt_count = tt_count;
        this.tt_height = tt_height;
        this.tt_peak = tt_peak;
        this.tt_trade = tt_trade;
    }
}
