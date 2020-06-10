package woodspring.springsimple.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import woodspring.springsimple.Entity.Person;
import woodspring.springsimple.exception.PersonNotFoundException;
import woodspring.springsimple.repository.PersonRepository;
import woodspring.springsimple.service.PersonService;

@Service
public class PersonImpl implements PersonService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PersonRepository personRepo;

	@Override
	public List<Person> getAllPerson() {
		List<Person> personList =   (List<Person>) personRepo.findAll();
		return personList;
	}

	@Override
	public Person getPersonById(Long personId) {
		//Optional<Person> person = personRepo.findById(personId);
		//return person.get();
		return personRepo.findById( personId)
				.orElseThrow( () -> new PersonNotFoundException( personId));
	}

	@Override
	public List<Person> insertPerson(int index) {
		return putPersonFromFile("nameList.csv");
	}
	
	private List<Person> putPersonFromFile(String filename) {
		List<Person> personList = new ArrayList<>();
		Path path = Paths.get(filename);
		try {
			personList = Files.lines(path)
						.filter( item -> (!item.startsWith("#")))
						.map( item -> {
								String[] name = item.split(" ");
								Person person = new Person(name[0], name[1]);
								person = personRepo.save( person);
								System.out.println("in list, Person:["+person+"]");
								return person;		
							})
						.collect(Collectors.toList());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("-----------------");
			personList.parallelStream().forEach(System.out::println);
			System.out.println("=================");

		}
		return personList;
		
	}

	@Override
	public Person replacePerson(Person newPerson, Long personId) {
		return personRepo.findById(personId)
				.map( person -> {
					person.setFirstname(newPerson.getFirstname());
					person.setLastname( newPerson.getLastname());
					return personRepo.save( person);
					
				})
				.orElseGet( () -> {
					newPerson.setId( personId);
					return personRepo.save( newPerson);
				});
		
	}

	@Override
	public Person postPerson(Person newPerson) {
		Person person = personRepo.save( newPerson);
		logger.info(" save "+ person.toString());
		return person;
	}

	
}
