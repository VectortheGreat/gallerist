package com.kadir.service;

import com.kadir.dto.DtoAccount;
import com.kadir.dto.DtoAccountIU;

public interface IAccountService {
    DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
