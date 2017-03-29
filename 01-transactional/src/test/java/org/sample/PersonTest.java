package org.sample;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.model.Address;
import org.sample.model.Person;
import org.sample.model.Phone;
import org.sample.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

	@Autowired
	private PersonService personService;
	
	@Before
	public void setup() {
		personService.init();
		
	}
	@Test
	public void testMustSaveAPerson() {
		int EXPECTED_NUMBER_OF_PEOPLE_PERSISTED = 1;

		Person mary = new Person("Mary", Collections.singletonList(new Address("1st Street", 10)),
                Collections.singletonList(new Phone("432")), Collections.emptyList());
		
		personService.save(mary);
		
		List<Person> peopleList = personService.list();
		
		assertThat(peopleList.size()).isEqualTo(EXPECTED_NUMBER_OF_PEOPLE_PERSISTED);
	}
	
}
