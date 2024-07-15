package com.digitalchief.tz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("carName")
    @Column(name = "car_name")
    private String name;

    @JsonProperty("carModel")
    @Column(name = "car_model")
    private String model;

    @JsonProperty("carAge")
    @Column(name = "car_age")
    private int age;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private CarShop carShop;

}