package backend.bd.carrental.rim.carrentalbackend.service;

import backend.bd.carrental.rim.carrentalbackend.exception.ResourceDoesnotExistException;
import backend.bd.carrental.rim.carrentalbackend.model.Car;
import backend.bd.carrental.rim.carrentalbackend.model.Rent;
import backend.bd.carrental.rim.carrentalbackend.repository.RentRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
public class RentService {
    private RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }


    public Rent saveRent(Rent rent) {
        return rentRepository.save(rent);
    }

    public Rent getRent(int id){
        return rentRepository.findById(id).get();
    }

    public Optional<Rent> getOptionalRent(int id){
        return rentRepository.findById(id);
    }
}
