package org.sample.model;


import java.util.Optional;

public class Address {
    private String street;
    private Integer number;
    private Optional<String> addOn = Optional.empty();

    public Address() { }

    public Address(String street, Integer number) {
        this(street, number, Optional.empty());
    }

    public Address(String street, Integer number, Optional<String> addOn) {
        this.street = street;
        this.number = number;
        this.addOn = addOn;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Optional<String> getAddOn() {
        return addOn;
    }

    public void setAddOn(Optional<String> addOn) {
        this.addOn = addOn;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Address{");
        sb.append("street='").append(street).append('\'');
        sb.append(", number=").append(number);
        sb.append(", addOn=").append(addOn);
        sb.append('}');
        return sb.toString();
    }
}
