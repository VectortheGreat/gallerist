package com.kadir.controller;

import com.kadir.dto.DtoCustomer;
import com.kadir.dto.DtoCustomerIU;

public interface IRestCustomerController {
    RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
