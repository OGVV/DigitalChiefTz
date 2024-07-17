package com.digitalchief.tz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @JsonProperty("car_brand")
    @Column(name = "car_brand")
    private String carBrand;

    @NonNull
    @JsonProperty("car_model")
    @Column(name = "car_model")
    private String model;

    @NonNull
    @JsonProperty("car_generation")
    @Column(name = "car_generation")
    private String carGeneration;

    @JsonProperty("car_age")
    @Column(name = "car_age")
    private int age;

    @JsonBackReference
    @ManyToOne
    private CarShop carShop;

    @JsonProperty("car_mileage")
    @Column(name = "car_mileage")
    private int carMileage;

    @JsonProperty("car_condition")
    @Column(name = "car_condition")
    private String carCondition;

    @JsonProperty("car_cost")
    @Column(name = "car_cost")
    private String carCost;

    @JsonProperty("sales")
    @Column(name = "sales")
    private boolean sales;
}