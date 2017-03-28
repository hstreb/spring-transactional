package org.sample.service;

import org.sample.converter.PersonConverter;
import org.sample.dao.AddressDao;
import org.sample.dao.EmailDao;
import org.sample.dao.PersonDao;
import org.sample.dao.PhoneDao;
import org.sample.entity.AddressEntity;
import org.sample.entity.EmailEntity;
import org.sample.entity.PersonEntity;
import org.sample.entity.PhoneEntity;
import org.sample.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private PersonDao personDao;
    private AddressDao addressDao;
    private PersonConverter personConverter;
    private PhoneDao phoneDao;
    private EmailDao emailDao;

    @Autowired
    public PersonService(PersonDao personDao, AddressDao addressDao, PersonConverter personConverter, PhoneDao phoneDao,
                         EmailDao emailDao) {
        this.personDao = personDao;
        this.addressDao = addressDao;
        this.personConverter = personConverter;
        this.phoneDao = phoneDao;
        this.emailDao = emailDao;
    }

    public void init() {
        log.info("Creating tables");
        personDao.init();
        addressDao.init();
        phoneDao.init();
        emailDao.init();
    }

    @Transactional
    public Person save(Person person) {
        log.info("Inserting person");
        PersonEntity personSaved = personDao.save(personConverter.convert(person));
        List<AddressEntity> addressesSaved = person.getAddresses().stream()
                .map(a -> personConverter.convert(a, personSaved.getId()))
                .map(addressDao::save)
                .collect(Collectors.toList());
        List<PhoneEntity> phonesSaved = person.getPhones().stream()
                .map(p -> personConverter.convert(p, personSaved.getId()))
                .map(phoneDao::save)
                .collect(Collectors.toList());
        List<EmailEntity> emailsSaved = person.getEmails().stream()
                .map(p -> personConverter.convert(p, personSaved.getId()))
                .map(emailDao::save)
                .collect(Collectors.toList());
        return personConverter.convert(personSaved, addressesSaved, phonesSaved, emailsSaved);
    }

    public List<Person> list() {
        return personDao.list().stream()
                .map(p -> personConverter.convert(p,
                        addressDao.list(p.getId()),
                        phoneDao.list(p.getId()),
                        emailDao.list(p.getId())))
                .collect(Collectors.toList());
    }
}
