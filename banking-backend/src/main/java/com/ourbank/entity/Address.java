package com.ourbank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.RequiredArgsConstructor;

@Embeddable
public class Address {

    @Column(name = "street_address", length = 100)
    private String streetAddress;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "state", length = 50)
    private String state;
    @Column(name = "zip_code", length = 5)
    private String zipCode;

    protected Address(){
    }

    public Address(String streetAddress, String city, String state, String zipCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
