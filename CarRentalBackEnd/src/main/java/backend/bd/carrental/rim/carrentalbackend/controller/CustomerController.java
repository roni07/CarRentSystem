package backend.bd.carrental.rim.carrentalbackend.controller;

import backend.bd.carrental.rim.carrentalbackend.exception.ResourceDoesnotExistException;
import backend.bd.carrental.rim.carrentalbackend.model.Customer;
import backend.bd.carrental.rim.carrentalbackend.model.Role;
import backend.bd.carrental.rim.carrentalbackend.repository.RoleRepository;
import backend.bd.carrental.rim.carrentalbackend.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static javafx.scene.input.KeyCode.R;

@RequestMapping(value = "customer")
@RestController
public class CustomerController {
    private CustomerService customerService;
    private RoleRepository roleRepository;

    public CustomerController(CustomerService customerService, RoleRepository roleRepository) {
        this.customerService = customerService;
        this.roleRepository = roleRepository;
    }

    @PostMapping(value = "registration")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        try {
            Role role = roleRepository.findByRole("USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            customer.setRoles(roles);
            Customer createdCustomer = customerService.createCustomer(customer);
            ResponseEntity<Customer> customerResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdCustomer);
            return customerResponseEntity;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PutMapping(value = "{updateUser}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String updateUser,
                                                   @RequestBody Customer customer){
        try{
            Customer updatedCustomer = customerService.updateCustomer(updateUser, customer);
            ResponseEntity<Customer> customerResponseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedCustomer);
            return customerResponseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }


    @DeleteMapping(value = "delete/{userName}")
    public ResponseEntity deleteCustomer(@PathVariable String userName) {
        try {
            customerService.deleteCustomer(userName);
            ResponseEntity customerResponseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Deleted!");
            return customerResponseEntity;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResourceDoesnotExistException(
                    "user name does not exist"));
        }
    }


    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(value = "all")
    public Iterable<Customer> getAllCustomer() {
        return customerService.getAll();
    }


    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(value = "{userName}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String userName) {
        try {
            Optional<Customer> optionalCustomer = customerService.getCustomer(userName);
            ResponseEntity customerResponseEntity = ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(optionalCustomer);
            return customerResponseEntity;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
