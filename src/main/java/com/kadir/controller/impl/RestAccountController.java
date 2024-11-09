package com.kadir.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kadir.controller.IRestAccountController;
import com.kadir.controller.RootEntity;
import com.kadir.dto.DtoAccount;
import com.kadir.dto.DtoAccountIU;
import com.kadir.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountController extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.saveAccount(dtoAccountIU));
    }

}
