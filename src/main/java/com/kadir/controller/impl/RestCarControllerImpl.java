package com.kadir.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kadir.controller.IRestControllerCar;
import com.kadir.controller.RootEntity;
import com.kadir.dto.DtoCar;
import com.kadir.dto.DtoCarIU;
import com.kadir.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestControllerCar {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }

}
