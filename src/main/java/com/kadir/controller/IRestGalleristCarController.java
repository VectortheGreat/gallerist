package com.kadir.controller;

import com.kadir.dto.DtoGalleristCar;
import com.kadir.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {
    RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
