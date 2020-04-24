package website.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import website.model.Client;

@Repository
public interface ClientsRepositroy extends CrudRepository<Client, Long> {

}
