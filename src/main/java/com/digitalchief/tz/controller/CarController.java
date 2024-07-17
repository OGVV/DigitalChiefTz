package com.digitalchief.tz.controller;

import com.digitalchief.tz.model.Car;
import com.digitalchief.tz.model.CarShop;
import com.digitalchief.tz.repository.CarRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CarRepository carRepository;
    Car car;

    @GetMapping(value = "/allCars", produces = "application/json")
    public String allCarsView() {
        List<Car> cars = carRepository.findAll();
        try {
            return objectMapper.writeValueAsString(cars);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/getCar/{id}", produces = "application/json")
    public String shopView(@PathVariable Long id) {
        Car car = carRepository.findById(id).orElseThrow();
        try {
            return objectMapper.writeValueAsString(car);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/addCar", produces = "application/json")
    public HttpStatus addShop(@RequestBody String newCar) {
        try {
            Car car = objectMapper.readValue(newCar, Car.class);
            carRepository.save(car);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return HttpStatus.CREATED;
    }

    @GetMapping(value = "/getCarsWithoutShop", produces = "application/json")
    public String choseCars() {
        List<Car> carsWithoutShop = carRepository.findByCarShopNull();
        try {
            return objectMapper.writeValueAsString(carsWithoutShop);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/updateCarInfo/{id}", produces = "application/json")
    public HttpStatus updateShop(@RequestBody String updateCar, @PathVariable Long id) {
        try {
            car = objectMapper.readValue(updateCar, Car.class);
            car.setId(id);
            carRepository.save(car);
            return HttpStatus.OK;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/deleteCar/{id}", produces = "application/json")
    public HttpStatus deleteShop(@PathVariable Long id) {
        car = carRepository.findById(id).orElseThrow();
        carRepository.delete(car);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/salesCarsList", produces = "application/json")
    public String salesCarView() {
        try {
            return objectMapper.writeValueAsString(carRepository.findBySalesTrue());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
