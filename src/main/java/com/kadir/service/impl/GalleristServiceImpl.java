package com.kadir.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadir.dto.DtoAddress;
import com.kadir.dto.DtoGallerist;
import com.kadir.dto.DtoGalleristIU;
import com.kadir.exception.BaseException;
import com.kadir.exception.ErrorMessage;
import com.kadir.exception.MessageType;
import com.kadir.model.Address;
import com.kadir.model.Gallerist;
import com.kadir.repository.AddressRepository;
import com.kadir.repository.GalleristRepository;
import com.kadir.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optAddress.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
        }
        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(optAddress.get());

        return gallerist;
    }

    @Override
    public DtoGallerist savGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();
        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));

        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
        dtoGallerist.setAddress(dtoAddress);
        dtoGallerist.setCreateTime(savedGallerist.getCreateTime().toString());
        dtoAddress.setCreateTime(savedGallerist.getAddress().getCreateTime().toString());

        return dtoGallerist;
    }

}
