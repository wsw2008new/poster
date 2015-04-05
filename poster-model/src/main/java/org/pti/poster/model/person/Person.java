package org.pti.poster.model.person;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;

}
