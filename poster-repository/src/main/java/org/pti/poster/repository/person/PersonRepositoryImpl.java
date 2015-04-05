package org.pti.poster.repository.person;


import org.pti.poster.model.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("personRepository")
public class PersonRepositoryImpl implements PersonRepository {

	@Override
	public Page<Person> getAll() {
		return null;
	}

	@Override
	public Person getById(String id) {
		return null;
	}

	@Override
	public List<Person> getByFirstNameLike(String firstName) {
		return null;
	}

	@Override
	public Person getByFirstNameEquals(String firstName) {
		return null;
	}

	@Override
	public Person save(Person person) {
		return null;
	}
}
