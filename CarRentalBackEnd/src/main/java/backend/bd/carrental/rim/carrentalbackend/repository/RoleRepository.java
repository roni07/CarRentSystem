package backend.bd.carrental.rim.carrentalbackend.repository;

import backend.bd.carrental.rim.carrentalbackend.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByRole(String userName);
}
