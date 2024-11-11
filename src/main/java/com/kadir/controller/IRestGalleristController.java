package com.kadir.controller;

import com.kadir.dto.DtoGallerist;
import com.kadir.dto.DtoGalleristIU;

public interface IRestGalleristController {

    RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
