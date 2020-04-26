package website.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import website.model.UserForm;


public interface UsersRepository extends CrudRepository<UserForm,String>  {
	
 
}
