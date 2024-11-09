package com.kadir.controller;

import com.kadir.dto.AuthRequest;
import com.kadir.dto.DtoUser;

public interface IRestAuthenticationController {
    RootEntity<DtoUser> register(AuthRequest input);
}
