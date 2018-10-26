package backend.bd.carrental.rim.carrentalbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int customerId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @Embedded
    private Address customerAddress;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles;

    public Customer(Customer customer) {
        this.userName = customer.getUserName();
        this.password = customer.getPassword();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.customerAddress = customer.getCustomerAddress();
        this.roles = customer.getRoles();
    }

}
