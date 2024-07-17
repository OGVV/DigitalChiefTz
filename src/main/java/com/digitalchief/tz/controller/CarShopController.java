package com.digitalchief.tz.controller;

import com.digitalchief.tz.model.Car;
import com.digitalchief.tz.model.CarShop;
import com.digitalchief.tz.repository.CarRepository;
import com.digitalchief.tz.repository.CarShopRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class CarShopController {

    @Autowired
    CarShopRepository carShopRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CarRepository carRepository;
    private CarShop carShop;

    @GetMapping(value = "/allShops", produces = "application/json")
    public String allShopsView() {
        List<CarShop> carShops;
        carShops = carShopRepository.findAll();
        try {
            return objectMapper.writeValueAsString(carShops);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/getShop/{id}", produces = "application/json")
    public String shopView(@PathVariable Long id) {
        carShop = carShopRepository.findById(id).orElseThrow();
        try {
            return objectMapper.writeValueAsString(carShop);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/createShop", produces = "application/json")
    public HttpStatus addShop(@RequestBody String createShop) {
        try {
            carShop = objectMapper.readValue(createShop, CarShop.class);
            carShopRepository.save(carShop);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return HttpStatus.CREATED;
    }

    @PostMapping(value = "/addCarsIntoShop/{id}", produces = "application/json")
    public HttpStatus chooseCars(@PathVariable Long id, @RequestBody String newCarsList) {
        carShop = carShopRepository.findById(id).orElseThrow();
        try {
            Set<Car> cars = objectMapper.readValue(newCarsList, new TypeReference<>() {
            });
            for (Car car :
                    cars) {
                carShop.addCar(car);
            }
            carShopRepository.save(carShop);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return HttpStatus.OK;
    }

    @PutMapping(value = "/updateShopInfo/{id}", produces = "application/json")
    public HttpStatus updateShop(@RequestParam(value = "name",required = false) Optional<String> name,@RequestParam(value = "address",required = false) Optional<String> address, @PathVariable Long id) {
        carShop = carShopRepository.findById(id).orElseThrow();
        carShop.setCarShopName(name.orElse(carShop.getCarShopName()));
        carShop.setCarShopAddress(address.orElse(carShop.getCarShopAddress()));
        carShop.setId(id);
        carShopRepository.save(carShop);
        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/deleteCarIntoShop/{id}", produces = "application/json")
    public HttpStatus deleteCarsIntoShop(@PathVariable Long id, @RequestParam(value = "delete_car_id") Long carId) {
        carShop = carShopRepository.findById(id).orElseThrow();
        carShop.removeCar(carRepository.findById(carId).orElseThrow());
        carShopRepository.save(carShop);
        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/deleteShop/{id}", produces = "application/json")
    public HttpStatus deleteShop(@PathVariable Long id) {
        carShop = carShopRepository.findById(id).orElseThrow();
        for (Car car:
             carShop.getCars()) {
            car.setCarShop(null);
        }
        carShop.setCars(null);
        carShopRepository.delete(carShop);
        return HttpStatus.OK;
    }
}
