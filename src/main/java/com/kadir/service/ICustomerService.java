package com.kadir.service;

import com.kadir.dto.DtoCustomer;
import com.kadir.dto.DtoCustomerIU;

public interface ICustomerService {

    DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
