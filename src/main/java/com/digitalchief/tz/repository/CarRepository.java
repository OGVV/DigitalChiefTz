package com.digitalchief.tz.repository;

import com.digitalchief.tz.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByCarShopNull();

    List<Car> findBySalesTrue();
}