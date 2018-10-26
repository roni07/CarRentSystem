package backend.bd.carrental.rim.carrentalbackend.controller;

import backend.bd.carrental.rim.carrentalbackend.model.Car;
import backend.bd.carrental.rim.carrentalbackend.model.Customer;
import backend.bd.carrental.rim.carrentalbackend.model.Rent;
import backend.bd.carrental.rim.carrentalbackend.service.CarService;
import backend.bd.carrental.rim.carrentalbackend.service.CustomerService;
import backend.bd.carrental.rim.carrentalbackend.service.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@RequestMapping(value = "rent")
@RestController
public class RentController {

    private RentService rentService;
    private CarService carService;
    private CustomerService customerService;

    public RentController(RentService rentService, CarService carService,
                          CustomerService customerService) {
        this.rentService = rentService;
        this.carService = carService;
        this.customerService = customerService;
    }

    @PostMapping(value = "booking")
    public ResponseEntity<Rent> bookCar(@RequestBody Rent rent) {
        return ResponseEntity.status(HttpStatus.OK).body(rentService.saveRent(rent));
    }

    @GetMapping(value = "rentCharge/{rentId}/{carId}")
    public double finalRent(@PathVariable int rentId, @PathVariable int carId) {
        Optional<Rent> optionalRent = rentService.getOptionalRent(rentId);
        Optional<Car> optionalCar = carService.getCar(carId);
        long day;
        double perDayCharge = 0;
        double totalCharge = 0;
        if (optionalRent.isPresent()) {
            LocalDate pickUpDate = optionalRent.get().getPickupDate();
            LocalDate dropOfDate = optionalRent.get().getDropOfDate();
            day = DAYS.between(pickUpDate, dropOfDate);
            if (optionalCar.isPresent()) {
                perDayCharge = optionalCar.get().getPerDayCharge();
            }
            if (day == 0) {
                totalCharge = perDayCharge;
            } else {
                totalCharge = day * perDayCharge;
            }
        }
        return totalCharge;
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping(value = "rental/{carId}")
    public Rent saveRental(@PathVariable int carId, @RequestBody Rent rent, Principal principal) {
        Optional<Car> car = carService.getCar(carId);

        rent.setCars(car.get());
        String username = principal.getName();
        Optional<Customer> customer = customerService.getCustomer(username);
        System.out.println(customer.get());

        double perDayCharge = 0;
        double totalCharge;
        LocalDate pickUpDate = rent.getPickupDate();
        LocalDate dropOfDate = rent.getDropOfDate();
        long day = DAYS.between(pickUpDate, dropOfDate);
        if (car.isPresent()) {
            car.get().setBooked(true);
            perDayCharge = car.get().getPerDayCharge();
        }
        if (day == 0) {
            totalCharge = perDayCharge;
        } else {
            totalCharge = day * perDayCharge;
        }
        rent.setRentCharge(totalCharge);
        Rent saveRent = rentService.saveRent(rent);
        return saveRent;
    }
}
