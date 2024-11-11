package com.kadir.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kadir.controller.IREstSaledCarController;
import com.kadir.controller.RootEntity;
import com.kadir.dto.DtoSaledCar;
import com.kadir.dto.DtoSalledCarIU;
import com.kadir.service.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IREstSaledCarController {

    @Autowired
    private ISaledCarService saledCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSalledCarIU dtoSalledCarIU) {
        return ok(saledCarService.buyCar(dtoSalledCarIU));
    }

}
