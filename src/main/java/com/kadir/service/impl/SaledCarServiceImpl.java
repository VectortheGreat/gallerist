package com.kadir.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadir.dto.CurrencyRatesResponse;
import com.kadir.dto.DtoCar;
import com.kadir.dto.DtoCustomer;
import com.kadir.dto.DtoGallerist;
import com.kadir.dto.DtoSaledCar;
import com.kadir.dto.DtoSalledCarIU;
import com.kadir.enums.CarStatusType;
import com.kadir.exception.BaseException;
import com.kadir.exception.ErrorMessage;
import com.kadir.exception.MessageType;
import com.kadir.model.Car;
import com.kadir.model.Customer;
import com.kadir.model.SaledCar;
import com.kadir.repository.CarRepository;
import com.kadir.repository.CustomerRepository;
import com.kadir.repository.GalleristRepository;
import com.kadir.repository.SaledCarRepository;
import com.kadir.service.ICurrencyRatesService;
import com.kadir.service.ISaledCarService;
import com.kadir.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    public BigDecimal convertCustomerAmountToUSD(Customer customer) {

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService
                .getCurrencyRates("11-10-2024", "11-10-2024");
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);

        return customerUSDAmount;
    }

    public boolean checkAmount(DtoSalledCarIU dtoSaledCarIU) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
        }

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

        // 37.000 35.000 = 0 1 -1 =2000
        if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0
                || customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
            return true;
        }
        return false;

    }

    private SaledCar createSaledCar(DtoSalledCarIU dtoSalledCarIU) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());

        saledCar.setCustomer(customerRepository.findById(dtoSalledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSalledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSalledCarIU.getCarId()).orElse(null));
        return saledCar;
    }

    public boolean checkCarStatus(Long carID) {
        Optional<Car> optCar = carRepository.findById(carID);
        if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
            return false;
        }
        return true;
    }

    public BigDecimal remainingCustomerAmount(Customer customer, Car car) {
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService
                .getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));

        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        return remaningCustomerUSDAmount.multiply(usd);
    }

    @Override
    public DtoSaledCar buyCar(DtoSalledCarIU dtoSalledCarIU) {
        if (!checkCarStatus(dtoSalledCarIU.getCarId())) {
            throw new BaseException(new ErrorMessage(MessageType.CAR_IS_SALED, dtoSalledCarIU.getCarId().toString()));
        }

        if (!checkAmount(dtoSalledCarIU)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
        }
        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSalledCarIU));

        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
        customerRepository.save(customer);

        return toDto(savedSaledCar);
    }

    public DtoSaledCar toDto(SaledCar saledCar) {
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(saledCar, dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(saledCar.getCar(), dtoCar);

        dtoSaledCar.setCustomerId(dtoCustomer);
        dtoSaledCar.setGalleristId(dtoGallerist);
        dtoSaledCar.setCarId(dtoCar);

        return dtoSaledCar;
    }

}
