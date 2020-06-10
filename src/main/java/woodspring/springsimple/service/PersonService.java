package woodspring.springsimple.service;

import java.util.List;

import woodspring.springsimple.Entity.Person;

public interface  PersonService {
	List<Person> getAllPerson();
	Person getPersonById(Long personId);
	List<Person> insertPerson(int index);
	Person postPerson(Person newPerson);
	Person replacePerson(Person newPerson, Long personId);
	

}
