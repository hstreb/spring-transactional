package org.sample.model;

import java.util.List;

public class Person {

    private Long id;
    private String name;
    private List<Address> addresses;
    private List<Phone> phones;
    private List<Email> emails;

    public Person() {
    }

    public Person(String name, List<Address> addresses, List<Phone> phones, List<Email> emails) {
        this.name = name;
        this.addresses = addresses;
        this.phones = phones;
        this.emails = emails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", addresses=").append(addresses);
        sb.append(", phones=").append(phones);
        sb.append(", emails=").append(emails);
        sb.append('}');
        return sb.toString();
    }
}
