package backend.bd.carrental.rim.carrentalbackend.service;

import backend.bd.carrental.rim.carrentalbackend.model.Customer;
import backend.bd.carrental.rim.carrentalbackend.model.CustomerRegistration;
import backend.bd.carrental.rim.carrentalbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerRegistrationService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findByUserName(customerName);
        optionalCustomer
                .orElseThrow(() -> new UsernameNotFoundException("user name is not found"));

        return optionalCustomer.map(CustomerRegistration:: new).get();
    }
}
