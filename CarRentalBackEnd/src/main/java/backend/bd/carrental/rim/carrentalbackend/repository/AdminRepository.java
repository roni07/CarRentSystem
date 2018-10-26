package backend.bd.carrental.rim.carrentalbackend.repository;

import backend.bd.carrental.rim.carrentalbackend.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {
}
