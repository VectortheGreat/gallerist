package com.kadir.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadir.dto.DtoAddress;
import com.kadir.dto.DtoCar;
import com.kadir.dto.DtoGallerist;
import com.kadir.dto.DtoGalleristCar;
import com.kadir.dto.DtoGalleristCarIU;
import com.kadir.exception.BaseException;
import com.kadir.exception.ErrorMessage;
import com.kadir.exception.MessageType;
import com.kadir.model.Car;
import com.kadir.model.Gallerist;
import com.kadir.model.GalleristCar;
import com.kadir.repository.CarRepository;
import com.kadir.repository.GalleristCarRepository;
import com.kadir.repository.GalleristRepository;
import com.kadir.service.IGalleristCarSerivce;

@Service
public class GalleristCarSerivceImpl implements IGalleristCarSerivce {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        if (!optGallerist.isPresent()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if (!optCar.isPresent()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());

        return galleristCar;
    }

    @Override
    public DtoGalleristCar savGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAddress dtoAddress = new DtoAddress();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);

        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);
        dtoGallerist.setAddress(dtoAddress);

        dtoGalleristCar.setCreateTime(savedGalleristCar.getCreateTime().toString());
        dtoGallerist.setCreateTime(savedGalleristCar.getGallerist().getCreateTime().toString());
        dtoCar.setCreateTime(savedGalleristCar.getCar().getCreateTime().toString());
        dtoAddress.setCreateTime(savedGalleristCar.getGallerist().getAddress().getCreateTime().toString());

        return dtoGalleristCar;
    }

}
