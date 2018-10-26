package backend.bd.carrental.rim.carrentalbackend.repository;


import backend.bd.carrental.rim.carrentalbackend.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
}
