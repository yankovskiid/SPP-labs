package com.bsuir.petition.bean.dto.city;

public class CityDTO {
    private long id;
    protected String name;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() { return country; }

    public void setCountry(String country) {
        this.country = country;
    }
}
