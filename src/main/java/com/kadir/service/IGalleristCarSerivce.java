package com.kadir.service;

import com.kadir.dto.DtoGalleristCar;
import com.kadir.dto.DtoGalleristCarIU;

public interface IGalleristCarSerivce {

    DtoGalleristCar savGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
