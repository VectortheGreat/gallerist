package com.kadir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCar extends DtoBase {

    private DtoCustomer customerId;

    private DtoGallerist galleristId;

    private DtoCar carId;
}
