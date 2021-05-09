package com.country.addresses.api.service.modal;

import java.util.StringJoiner;

public class Address {

    private Long id;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    public Address() {
    }

    public Address(Long id, String name, String address1, String address2, String city, String state, String zipcode,
                   String country) {
        this.id = id;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipcode;
    }

    public void setZipCode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Display results
    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]").add("id=" + id)
                .add("Address1 ='" + address1 + "'").add("Address2 ='" + address2 + "'").add("City ='" + city + "'")
                .add("State ='" + state + "'").add("Zip Code ='" + zipcode + "'").add("Country ='" + country + "'")
                .toString();
    }
}
