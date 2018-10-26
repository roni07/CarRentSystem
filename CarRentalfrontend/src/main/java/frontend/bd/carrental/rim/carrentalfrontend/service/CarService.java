package frontend.bd.carrental.rim.carrentalfrontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import frontend.bd.carrental.rim.carrentalfrontend.model.Car;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Service
public class CarService {


    public List<Car> getAllCar() {


        RestTemplate restTemplate = new RestTemplate();
        Car car = null;
        HttpEntity<Car> carHttpEntity = new HttpEntity<>(car);
        ResponseEntity<List<Car>> customerResponseEntity = restTemplate.exchange(
                "http://localhost/8083/car/all",
                HttpMethod.GET,
                carHttpEntity,
                new ParameterizedTypeReference<List<Car>>() {});

        return customerResponseEntity.getBody();
    }


    /*private ObjectMapper objectMapper;

    public CarService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Car> getAllCar(){
        Car[] cars = null;
        try {
            URL url = new URL("http://localhost:8083/car/all");
            cars = objectMapper.readValue(url, Car[].class);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        List<Car> carList = Arrays.asList(cars);
        return carList;
    }

    public List<Car> notBooked(){
        Car[] cars = null;
        try {
            URL url = new URL("http://localhost:8083/car/notBooked");
            cars = objectMapper.readValue(url, Car[].class);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        List<Car> carList = Arrays.asList(cars);
        return carList;
    }*/
}
