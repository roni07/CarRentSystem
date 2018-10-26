package frontend.bd.carrental.rim.carrentalfrontend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Branch {
    private int branchId;
    private String branchName;
    private Address branchAddress;
}
