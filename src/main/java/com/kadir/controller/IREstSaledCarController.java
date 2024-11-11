package com.kadir.controller;

import com.kadir.dto.DtoSaledCar;
import com.kadir.dto.DtoSalledCarIU;

public interface IREstSaledCarController {

    RootEntity<DtoSaledCar> buyCar(DtoSalledCarIU dtoSalledCarIU);
}
