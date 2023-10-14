package com.example.interchange.pojo;

/**
 * @author 秃头小刘
 * @date 2022-05-06 7:28 PM
 */


public class CompanyNode {
    String id;
    String companyId;
    String nd_name;
    String nd_location;
    String nd_state;
    String nd_height;
    String nd_hash;
    String nd_timestamp;
    String nd_code;

    public CompanyNode() {
    }

    @Override
    public String toString() {
        return "CompanyNode{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", nd_name='" + nd_name + '\'' +
                ", nd_location='" + nd_location + '\'' +
                ", nd_state='" + nd_state + '\'' +
                ", nd_height='" + nd_height + '\'' +
                ", nd_hash='" + nd_hash + '\'' +
                ", nd_timestamp='" + nd_timestamp + '\'' +
                ", nd_code='" + nd_code + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getNd_name() {
        return nd_name;
    }

    public void setNd_name(String nd_name) {
        this.nd_name = nd_name;
    }

    public String getNd_location() {
        return nd_location;
    }

    public void setNd_location(String nd_location) {
        this.nd_location = nd_location;
    }

    public String getNd_state() {
        return nd_state;
    }

    public void setNd_state(String nd_state) {
        this.nd_state = nd_state;
    }

    public String getNd_height() {
        return nd_height;
    }

    public void setNd_height(String nd_height) {
        this.nd_height = nd_height;
    }

    public String getNd_hash() {
        return nd_hash;
    }

    public void setNd_hash(String nd_hash) {
        this.nd_hash = nd_hash;
    }

    public String getNd_timestamp() {
        return nd_timestamp;
    }

    public void setNd_timestamp(String nd_timestamp) {
        this.nd_timestamp = nd_timestamp;
    }

    public String getNd_code() {
        return nd_code;
    }

    public void setNd_code(String nd_code) {
        this.nd_code = nd_code;
    }

    public CompanyNode(String id, String companyId, String nd_name, String nd_location, String nd_state, String nd_height, String nd_hash, String nd_timestamp, String nd_code) {
        this.id = id;
        this.companyId = companyId;
        this.nd_name = nd_name;
        this.nd_location = nd_location;
        this.nd_state = nd_state;
        this.nd_height = nd_height;
        this.nd_hash = nd_hash;
        this.nd_timestamp = nd_timestamp;
        this.nd_code = nd_code;
    }
}
