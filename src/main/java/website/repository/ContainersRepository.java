package website.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import website.model.Port;

@Repository 
public interface ContainersRepository extends CrudRepository<Port, String> {

	
}
