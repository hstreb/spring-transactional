package org.sample.converter;

import org.sample.entity.AddressEntity;
import org.sample.entity.EmailEntity;
import org.sample.entity.PersonEntity;
import org.sample.entity.PhoneEntity;
import org.sample.model.Address;
import org.sample.model.Email;
import org.sample.model.Person;
import org.sample.model.Phone;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    public PersonEntity convert(Person person) {
        PersonEntity entity = new PersonEntity();
        entity.setId(person.getId());
        entity.setName(person.getName());
        return entity;
    }

    public Person convert(PersonEntity entity, List<AddressEntity> addressesEntities, List<PhoneEntity> phoneEntities,
                          List<EmailEntity> emailEntities) {
        Person person = new Person();
        person.setId(entity.getId());
        person.setName(entity.getName());
        List<Address> addresses = addressesEntities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
        person.setAddresses(addresses);
        List<Phone> phones = phoneEntities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
        person.setPhones(phones);
        List<Email> emails = emailEntities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
        person.setEmails(emails);
        return person;
    }

    public AddressEntity convert(Address address, Long personId) {
        return new AddressEntity(personId, address.getStreet(), address.getNumber(), address.getAddOn());
    }

    public Address convert(AddressEntity entity) {
        return new Address(entity.getStreet(), entity.getNumber(), entity.getAddOn());
    }

    public PhoneEntity convert(Phone phone, Long personId) {
        return new PhoneEntity(personId, phone.getNumber());
    }

    public Phone convert(PhoneEntity phone) {
        return new Phone(phone.getNumber());
    }

    public EmailEntity convert(Email email, Long personId) {
        return new EmailEntity(personId, email.getEmail());
    }

    public Email convert(EmailEntity email) {
        return new Email(email.getEmail());
    }
}
