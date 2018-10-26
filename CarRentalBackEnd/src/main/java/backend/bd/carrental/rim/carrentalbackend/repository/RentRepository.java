package backend.bd.carrental.rim.carrentalbackend.repository;

import backend.bd.carrental.rim.carrentalbackend.model.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends CrudRepository<Rent, Integer> {
}
