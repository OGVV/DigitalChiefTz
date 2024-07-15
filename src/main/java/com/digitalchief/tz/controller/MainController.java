package com.digitalchief.tz.controller;

import com.digitalchief.tz.model.CarShop;
import com.digitalchief.tz.repository.CarShopRepo;
import com.digitalchief.tz.repository.CarShopRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    @Autowired
    CarShopRepo carShopRepository;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = "/",produces = "application/json")
    public String mainPageView() {
        CarShop carShop = carShopRepository.findById(1L).orElseThrow();
        String str;
        try {
            str = objectMapper.writeValueAsString(carShop);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}
