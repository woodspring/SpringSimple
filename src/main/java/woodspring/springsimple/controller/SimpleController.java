package woodspring.springsimple.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import woodspring.springsimple.Entity.Person;
import woodspring.springsimple.service.ConcurrencyService;
import woodspring.springsimple.service.PersonService;

@RestController
public class SimpleController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private PersonService personImpl;
	
	@Autowired
	@Qualifier("ExecService")
	ConcurrencyService execSrv;
	
	@Autowired
	@Qualifier("ExecutorService")
	ConcurrencyService excutorService;
	
	@GetMapping("/person/before")
	public List<Person> before() {
		logger.info("person before ==> ");
		List<Person> personList = personImpl.insertPerson(10);
		personList.parallelStream().forEach(System.out::println);
		return personImpl.insertPerson(10);
	}
	
	@GetMapping("/person/all")
	public List<Person> all() {
		logger.info("person before ==> ");
		List<Person> personList = personImpl.getAllPerson();
		personList.parallelStream().forEach(System.out::println);
		return personList;
	}
	
	@GetMapping("/person/id/{personId}")
	public Person getPersonl(@PathVariable Long personId) {
		logger.info("person id  ==> "+ personId);
		Person person = personImpl.getPersonById(personId);
		logger.info("Person:["+ person.toString()+"]");;
		return person;
	}
	
	@PostMapping(path="/person/post/json", consumes = "application/json")
	public Person postPerson(@RequestBody Person newPerson) {
		logger.info("postPerson  ==> "+ newPerson.toString());
		Person person = personImpl.postPerson(newPerson);
		logger.info("Person:["+ person.toString()+"]");;
		return person;
	}
	
	@PostMapping(path="/person/post/urlencode", consumes = "application/x-www-form-urlencoded")
	public Person postPerson2( Person newPerson) {
		logger.info("postPerson2  ==> "+ newPerson.toString());
		Person person = personImpl.postPerson(newPerson);
		logger.info("Person:["+ person.toString()+"]");;
		return person;
	}

	
	@PutMapping("/person/put/{personId}")
	public Person putPerson(@RequestBody Person newPerson, @PathVariable Long personId) {
		logger.info("putPerson  ==> "+ newPerson.toString());
		Person person = personImpl.replacePerson(newPerson, personId);
		logger.info("Person:["+ person.toString()+"]");;
		return person;
	}
	
	@GetMapping("/worker/{taskNum}")
	public String runExecService(@PathVariable Long taskNum) {
		logger.info("runExecService taskNum  ==> "+ taskNum);
		String result = execSrv.runConfdInterval( taskNum);
		logger.info("ConcurrencyService END:" + result);;
		return result;
	}
	
	@GetMapping("/future/{taskNum}")
	public String runExecutorService(@PathVariable Long taskNum) {
		logger.info("runExecutorService taskNum  ==> "+ taskNum);
		String result = excutorService.runConfdInterval( taskNum);
		logger.info("runExecutorService END:" + result);;
		return result;
	}
	

}
