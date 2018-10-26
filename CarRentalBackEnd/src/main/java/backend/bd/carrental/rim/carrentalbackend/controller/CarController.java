package backend.bd.carrental.rim.carrentalbackend.controller;

import backend.bd.carrental.rim.carrentalbackend.model.Branch;
import backend.bd.carrental.rim.carrentalbackend.model.Car;
import backend.bd.carrental.rim.carrentalbackend.service.BranchService;
import backend.bd.carrental.rim.carrentalbackend.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "car")
@RestController
public class CarController {
    private CarService carService;
    private BranchService branchService;

    public CarController(CarService carService, BranchService branchService) {
        this.carService = carService;
        this.branchService = branchService;
    }

    @PostMapping(value = "save/{branchId}")
    public ResponseEntity<Car> saveCar(@PathVariable int branchId, @RequestBody Car car) {
        try {
            Branch branch = branchService.getBranch(branchId);
            car.setBranch(branch);
            Car createdCar = carService.createCar(car);
            branch.getCars().add(createdCar);
            branchService.createCarUsingBranch(branchId, branch);
            ResponseEntity<Car> carResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdCar);
            return carResponseEntity;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "all")
    public Iterable<Car> getCars() {
        return carService.getAllCar();
    }

    @GetMapping(value = "isBooked/{carId}")
    public ResponseEntity<Boolean> isCarBooked(@PathVariable int carId) {
        Optional<Car> optionalCar = carService.getCar(carId);
        if (!optionalCar.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }
        boolean booked = carService.isBooked(optionalCar.get());
        if (booked) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.ok().body(false);
        }
    }


    @GetMapping(value = "notBooked")
    public List<Car> getAllNotBookedCar() {
        List<Car> carList = carService.getAllforBooked();
        List<Car> newCarList = new ArrayList<>();
        for(int i = 0; i < carList.size(); i++){
            if(!carList.get(i).isBooked()){
                   newCarList.add(carList.get(i));
            }
        }
        return newCarList;
    }

    @GetMapping(value = "booked")
    public List<Car> getAllBookedCar(){
        List<Car> carList = carService.getAllforBooked();
        List<Car> newCarList = new ArrayList<>();
        for(int i = 0; i < carList.size(); i++){
            if(carList.get(i).isBooked()){
                newCarList.add(carList.get(i));
            }
        }
        return newCarList;
    }
}