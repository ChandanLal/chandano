package com.example.nobroke;

public class DataModel {
    private String rent,propertySize,propertyTitle;

    public DataModel() {
    }

    public DataModel(String rent, String propertySize, String propertyTitle) {
        this.rent = rent;
        this.propertySize = propertySize;
        this.propertyTitle = propertyTitle;
    }

    public String getRent() {
        return rent;
    }

    public String getPropertySize() {
        return propertySize;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public void setPropertySize(String propertySize) {
        this.propertySize = propertySize;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }
}
