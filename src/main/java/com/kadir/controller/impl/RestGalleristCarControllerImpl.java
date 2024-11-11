package com.kadir.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kadir.controller.IRestGalleristCarController;
import com.kadir.controller.RootEntity;
import com.kadir.dto.DtoGalleristCar;
import com.kadir.dto.DtoGalleristCarIU;
import com.kadir.service.IGalleristCarSerivce;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarSerivce galleristCarSerivce;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarSerivce.savGalleristCar(dtoGalleristCarIU));
    }

}
