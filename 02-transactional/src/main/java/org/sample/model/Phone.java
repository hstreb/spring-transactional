package org.sample.model;

public class Phone {
    private String number;

    public Phone(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Phone{");
        sb.append("number='").append(number).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
