package com.rental.service;

import com.rental.dto.CarDto;
import com.rental.entity.CarEntity;
import com.rental.dto.CustomerDto;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface RentService {

  CarEntity addNewCar(CarDto carDto);

  void excludeCar(Boolean isExclusionPermanent, Long carId);

  void includeCar(Long carId);

  void rentCar(@RequestBody CustomerDto customer, @PathVariable Long carId);

  void takeCarBack(Long customerId, Long carId);

  List<CarDto> getAllCars(String carStatus);

}
