package frontend.bd.carrental.rim.carrentalfrontend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    private String streetNumber;
    private String streetName;
    private String city;
    private String postalCode;
}
