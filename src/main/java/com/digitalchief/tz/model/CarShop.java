package com.digitalchief.tz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "car_shop")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("carShopAddress")
    @Column(name = "car_shop_address")
    private String carShopAddress;

    @JsonProperty("cars")
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "carShop",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Car> cars = new HashSet<>();

    @JsonProperty("carShopName")
    @Column(name = "car_shop_name")
    private String carShopName;

}