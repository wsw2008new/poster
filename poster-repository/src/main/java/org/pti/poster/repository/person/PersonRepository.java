package org.pti.poster.repository.person;

import org.pti.poster.model.person.Person;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonRepository {

	Page<Person> getAll();

	Person getById(String id);

	List<Person> getByFirstNameLike(String firstName);

	Person getByFirstNameEquals(String firstName);

	Person save(Person person);
}
