package com.kadir.service;

import com.kadir.dto.DtoCar;
import com.kadir.dto.DtoCarIU;

public interface ICarService {

    DtoCar saveCar(DtoCarIU dtoCarIU);
}
