package org.sample.entity;

import java.util.Optional;

public class AddressEntity {
    private Long personId;
    private String street;
    private Integer number;
    private Optional<String> addOn = Optional.empty();

    public AddressEntity(Long personId, String street, Integer number) {
        this(personId, street, number, Optional.empty());
    }

    public AddressEntity(Long personId, String street, Integer number, Optional<String> addOn) {
        this.personId = personId;
        this.street = street;
        this.number = number;
        this.addOn = addOn;
    }

    public AddressEntity() { }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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
        final StringBuffer sb = new StringBuffer("AddressEntity{");
        sb.append("personId=").append(personId);
        sb.append(", street='").append(street).append('\'');
        sb.append(", number=").append(number);
        sb.append(", addOn=").append(addOn);
        sb.append('}');
        return sb.toString();
    }
}
