package com.ziooo.dao;

import java.util.List;

import com.ziooo.model.Person;

public interface PersonDAO {

	Person createPerson(String name, double salaryPerSec);

	List<Person> getAllPersons();

	Person getPersonById(long idPerson);

	int updatePerson(Person person);

	int deletePerson(Person person);

}
