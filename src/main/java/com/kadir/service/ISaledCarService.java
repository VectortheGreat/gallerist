package com.kadir.service;

import com.kadir.dto.DtoSaledCar;
import com.kadir.dto.DtoSalledCarIU;

public interface ISaledCarService {

    DtoSaledCar buyCar(DtoSalledCarIU dtoSalledCarIU);
}
