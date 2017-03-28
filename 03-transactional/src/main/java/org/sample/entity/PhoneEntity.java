package org.sample.entity;

public class PhoneEntity {
    private Long personId;
    private String number;

    public PhoneEntity() {}

    public PhoneEntity(String number) {
        this.number = number;
    }

    public PhoneEntity(Long personId, String number) {
        this.personId = personId;
        this.number = number;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PhoneEntity{");
        sb.append("personId=").append(personId);
        sb.append(", number='").append(number).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
