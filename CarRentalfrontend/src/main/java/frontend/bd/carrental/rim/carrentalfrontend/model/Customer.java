package frontend.bd.carrental.rim.carrentalfrontend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Address customerAddress = new Address();
}
