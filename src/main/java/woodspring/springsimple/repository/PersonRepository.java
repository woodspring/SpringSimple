package woodspring.springsimple.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import woodspring.springsimple.Entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{

}
