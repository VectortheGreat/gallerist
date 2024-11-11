package com.kadir.service;

import com.kadir.dto.DtoGallerist;
import com.kadir.dto.DtoGalleristIU;

public interface IGalleristService {

    DtoGallerist savGallerist(DtoGalleristIU dtoGalleristIU);
}
