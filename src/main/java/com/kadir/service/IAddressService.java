package com.kadir.service;

import com.kadir.dto.DtoAddress;
import com.kadir.dto.DtoAddressIU;

public interface IAddressService {

    DtoAddress savAddress(DtoAddressIU dtoAddressIU);
}
