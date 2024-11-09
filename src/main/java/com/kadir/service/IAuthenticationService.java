package com.kadir.service;

import com.kadir.dto.AuthRequest;
import com.kadir.dto.DtoUser;

public interface IAuthenticationService {
    DtoUser register(AuthRequest input);
}
