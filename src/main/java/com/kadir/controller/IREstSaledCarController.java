package com.kadir.controller;

import com.kadir.dto.DtoSaledCar;
import com.kadir.dto.DtoSaledCarIU;

public interface IREstSaledCarController {

    RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSalledCarIU);
}
