package org.sample.entity;

public class EmailEntity {
    private Long personId;
    private String email;

    public EmailEntity(String email) {
        this.email = email;
    }

    public EmailEntity(Long personId, String email) {
        this.personId = personId;
        this.email = email;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PhoneEntity{");
        sb.append("personId=").append(personId);
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
