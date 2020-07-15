package woodspring.springsimple.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import woodspring.springsimple.Entity.Statistics;

@Repository
public interface StatisticsDAO  extends CrudRepository<Statistics, Long>{

}
