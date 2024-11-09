package com.kadir.controller;

import com.kadir.dto.DtoAddress;
import com.kadir.dto.DtoAddressIU;

public interface IRestAddressController {

    RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
