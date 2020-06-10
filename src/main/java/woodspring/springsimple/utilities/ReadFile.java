package woodspring.springsimple.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import woodspring.springsimple.Entity.Person;

@Component
public class ReadFile {
	BufferedReader bufReader = null;
	
	
	public List<Person> readPersonFile(String filename) {
		List<Person> personList = new ArrayList<>();
		try {
			bufReader = new BufferedReader( new FileReader( filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		return personList;
	}
}
