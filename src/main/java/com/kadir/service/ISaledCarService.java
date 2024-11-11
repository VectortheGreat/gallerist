package com.kadir.service;

import com.kadir.dto.DtoSaledCar;
import com.kadir.dto.DtoSaledCarIU;

public interface ISaledCarService {

    DtoSaledCar buyCar(DtoSaledCarIU dtoSalledCarIU);
}
