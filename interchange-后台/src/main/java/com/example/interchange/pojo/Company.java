package com.example.interchange.pojo;

/**
 * @author 秃头小刘
 * @date 2022-05-06 4:44 PM
 */


public class Company {
    String id;
    String company_name;
    String company_logo;

    public Company() {
    }

    public Company(String id, String company_name, String company_logo) {
        this.id = id;
        this.company_name = company_name;
        this.company_logo = company_logo;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", company_name='" + company_name + '\'' +
                ", company_logo='" + company_logo + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }
}
