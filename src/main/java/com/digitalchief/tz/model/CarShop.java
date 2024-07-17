package com.digitalchief.tz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "car_shop")
public class CarShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("car_shop_address")
    @Column(name = "car_shop_address")
    private String carShopAddress;

    @JsonProperty("cars")
    @OneToMany(mappedBy = "carShop",cascade = CascadeType.ALL)
    List<Car> cars;

    @JsonProperty("car_shop_name")
    @Column(name = "car_shop_name")
    private String carShopName;

    public void addCar(Car car) {
        cars.add(car);
        car.setCarShop(this);
    }
    public void removeCar(Car car) {
        car.setCarShop(null);
    }
}