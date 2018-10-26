package frontend.bd.carrental.rim.carrentalfrontend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private int carId;
    private String carModel;
    private String carClass;
    private String carColor;
    private boolean booked;
    private double perDayCharge;
}
