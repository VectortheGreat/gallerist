package com.kadir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCar extends DtoBase {

    private DtoGallerist gallerist;

    private DtoCar car;
}
