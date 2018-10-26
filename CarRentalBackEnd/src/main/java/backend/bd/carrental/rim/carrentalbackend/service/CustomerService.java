package backend.bd.carrental.rim.carrentalbackend.service;

import backend.bd.carrental.rim.carrentalbackend.exception.ResourceAlreadyExistException;
import backend.bd.carrental.rim.carrentalbackend.exception.ResourceDoesnotExistException;
import backend.bd.carrental.rim.carrentalbackend.model.Customer;
import backend.bd.carrental.rim.carrentalbackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerFormRepository) {
        this.customerRepository = customerFormRepository;
    }

    public Customer createCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByUserName(customer.getUserName());
        if (optionalCustomer.isPresent()) {
            throw new ResourceAlreadyExistException("user name already exist " + customer.getUserName());
        } else {
            return customerRepository.save(customer);
        }
    }


    public void deleteCustomer(String userName) {
        customerRepository.deleteById(userName);
    }

    public Customer updateCustomer(String userName, Customer customer){
        Optional<Customer> optionalCustomer = customerRepository.findById(userName);
        if(optionalCustomer.isPresent()){
            customer.setUserName(userName);
            return customerRepository.save(customer);
        }
        else{
            throw new ResourceDoesnotExistException("user does not exist");
        }
    }

    public Optional<Customer> getCustomer(String userName) {
        Optional<Customer> optionalCustomer = customerRepository.findByUserName(userName);
        if (optionalCustomer.isPresent()) {
            return customerRepository.findByUserName(userName);
        } else {
            throw new ResourceDoesnotExistException("user name does not exist " + userName);
        }
    }

    public Iterable<Customer> getAll() {
        return customerRepository.findAll();
    }

}
