package org.sample;

import org.sample.model.Address;
import org.sample.model.Email;
import org.sample.model.Person;
import org.sample.model.Phone;
import org.sample.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(exclude = { DataSourceTransactionManagerAutoConfiguration.class })
public class App implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    private PersonService personService;

    @Autowired
    public App(PersonService personService) {
        this.personService = personService;
    }

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        personService.init();
        getPeople().forEach(this::save);
        List<Person> list = personService.list();
        log.info("people count: {}", list.size());
        list.stream()
                .map(Person::toString)
                .forEach(log::info);

    }

    private List<Person> getPeople() {
        Person mary = new Person("Mary", Collections.singletonList(new Address("1st Street", 10)),
                Collections.singletonList(new Phone("432")), Collections.emptyList());
        Person john = new Person("John", Collections.singletonList(new Address(null, 5)),
                Collections.emptyList(), Collections.emptyList());
        Person peter = new Person("Peter", Collections.singletonList(new Address("5th Avenue", 5,
                Optional.of("house 3"))), Collections.emptyList(), Collections.emptyList());
        Person joe = new Person("Joe", Collections.singletonList(new Address("1st Avenue", 15)),
                Collections.singletonList(new Phone(null)), Collections.emptyList());
        Person bruce = new Person("Bruce", Collections.singletonList(new Address("1st Avenue", 30)),
                Collections.singletonList(new Phone("1232")), Collections.singletonList(new Email("bruce@email.com")));
        return Arrays.asList(mary, john, peter, joe, bruce);
    }

    private void save(Person person) {
        try {
            personService.save(person);
        } catch (Exception e) {
            log.error("Error to save person: {}", person);
        }
    }
}
