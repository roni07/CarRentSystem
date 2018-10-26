package backend.bd.carrental.rim.carrentalbackend.repository;

import backend.bd.carrental.rim.carrentalbackend.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    Optional<Customer> findByUserName(String userName);

}
