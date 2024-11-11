package com.kadir.controller;

import com.kadir.dto.DtoCar;
import com.kadir.dto.DtoCarIU;

public interface IRestControllerCar {
    RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
