package com.example.interchange.pojo;

/**
 * @author 秃头小刘
 * @date 2022-05-06 8:32 PM
 */


public class CompanyTrade {
    String id;
    String companyId;
    String td_type;
    String td_ip;
    String td_valid;
    String td_height;
    String td_hash;
    String td_timestamp;
    String td_code;

    public CompanyTrade() {
    }

    @Override
    public String toString() {
        return "CompanyTradeService{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", td_type='" + td_type + '\'' +
                ", td_ip='" + td_ip + '\'' +
                ", td_valid='" + td_valid + '\'' +
                ", td_height='" + td_height + '\'' +
                ", td_hash='" + td_hash + '\'' +
                ", td_timestamp='" + td_timestamp + '\'' +
                ", td_code='" + td_code + '\'' +
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

    public String getTd_type() {
        return td_type;
    }

    public void setTd_type(String td_type) {
        this.td_type = td_type;
    }

    public String getTd_ip() {
        return td_ip;
    }

    public void setTd_ip(String td_ip) {
        this.td_ip = td_ip;
    }

    public String getTd_valid() {
        return td_valid;
    }

    public void setTd_valid(String td_valid) {
        this.td_valid = td_valid;
    }

    public String getTd_height() {
        return td_height;
    }

    public void setTd_height(String td_height) {
        this.td_height = td_height;
    }

    public String getTd_hash() {
        return td_hash;
    }

    public void setTd_hash(String td_hash) {
        this.td_hash = td_hash;
    }

    public String getTd_timestamp() {
        return td_timestamp;
    }

    public void setTd_timestamp(String td_timestamp) {
        this.td_timestamp = td_timestamp;
    }

    public String getTd_code() {
        return td_code;
    }

    public void setTd_code(String td_code) {
        this.td_code = td_code;
    }

    public CompanyTrade(String id, String companyId, String td_type, String td_ip, String td_valid, String td_height, String td_hash, String td_timestamp, String td_code) {
        this.id = id;
        this.companyId = companyId;
        this.td_type = td_type;
        this.td_ip = td_ip;
        this.td_valid = td_valid;
        this.td_height = td_height;
        this.td_hash = td_hash;
        this.td_timestamp = td_timestamp;
        this.td_code = td_code;
    }
}
