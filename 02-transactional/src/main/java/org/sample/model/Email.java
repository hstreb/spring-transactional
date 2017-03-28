package org.sample.model;

public class Email {
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Phone{");
        sb.append("email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
