package com.kadir.controller;

import com.kadir.dto.DtoAccount;
import com.kadir.dto.DtoAccountIU;

public interface IRestAccountController {

    RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
