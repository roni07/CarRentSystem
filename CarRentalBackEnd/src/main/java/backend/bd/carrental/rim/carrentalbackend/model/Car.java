package backend.bd.carrental.rim.carrentalbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Car {
    @Id
    private int carId;
    private String carModel;
    @Enumerated
    private CarClass carClass;
    private String carColor;
    private String numberPlate;
    private boolean booked;
    private double perDayCharge;

    @ManyToOne(targetEntity = Branch.class)
    private Branch branch;

    public Car(double perDayCharge) {
        this.perDayCharge = perDayCharge;
    }
}
