package backend.bd.carrental.rim.carrentalbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Branch {
    @Id
    private int branchId;
    private String branchName;
    @Embedded
    private Address branchAddress;
    @JsonIgnore
    @OneToMany
    private List<Car> cars;
}