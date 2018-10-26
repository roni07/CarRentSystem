package backend.bd.carrental.rim.carrentalbackend.service;

import backend.bd.carrental.rim.carrentalbackend.exception.ResourceAlreadyExistException;
import backend.bd.carrental.rim.carrentalbackend.exception.ResourceDoesnotExistException;
import backend.bd.carrental.rim.carrentalbackend.model.Car;
import backend.bd.carrental.rim.carrentalbackend.repository.CarRepository;
import net.bytebuddy.description.method.ParameterList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car){
        Optional<Car> optionalCar = carRepository.findById(car.getCarId());
        if(optionalCar.isPresent()){
            throw new ResourceAlreadyExistException("branch is exist with id = " +car.getCarId());
        }
        else {
            return carRepository.save(car);
        }
    }

    public Iterable<Car> getAllCar(){
        return carRepository.findAll();
    }

    public Optional<Car> getCar(int id){
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()){
            return carRepository.findById(id);
        }
        else{
            throw new ResourceDoesnotExistException("Car does not exist!");
        }
    }

    public Boolean isBooked(Car car){
        Optional<Car> optionalCar = carRepository.findById(car.getCarId());
            if(!optionalCar.isPresent()){
                throw new ResourceDoesnotExistException("car does not exist! ");
            }
            if(optionalCar.get().isBooked()){
                return true;
            }
            else{
                return false;
            }
    }

    public List<Car> getAllforBooked() {
        List<Car> carList = new ArrayList<>();
        carRepository.findAll().forEach(carList::add);
        return carList;
    }

    //for rent
    public Car getCarForRent(int id){
        return carRepository.findById(id).get();
    }

}
