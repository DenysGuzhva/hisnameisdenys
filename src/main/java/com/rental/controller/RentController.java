package com.rental.controller;

import com.rental.dto.CarDto;
import com.rental.entity.CarEntity;
import com.rental.dto.CustomerDto;
import com.rental.service.RentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rental")
@AllArgsConstructor
public class RentController {

  private final RentService rentalService;

  @PostMapping("/car")
  public CarEntity addNewCar(@RequestBody CarDto carDto) {
    return rentalService.addNewCar(carDto);
  }

  @PutMapping("/car/exclude/{carId}")
  public void excludeCar(
      @RequestParam(value = "isExclusionPermanent", defaultValue = "false") Boolean isExclusionPermanent,
      @PathVariable Long carId) {
    rentalService.excludeCar(isExclusionPermanent, carId);
  }

  @PutMapping("/car/include/{carId}")
  public void includeCar(@PathVariable Long carId) {
    rentalService.includeCar(carId);
  }

  @PutMapping("/rent-car/{carId}")
  public ResponseEntity<String> rentCar(@RequestBody CustomerDto customer, @PathVariable Long carId) {
    try {
      rentalService.rentCar(customer, carId);
      return ResponseEntity.ok().body("Success!");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/take-back-car/{customerId}/{carId}")
  public void takeCarBack(@PathVariable Long customerId, @PathVariable Long carId) {
    rentalService.takeCarBack(customerId, carId);
  }

  @GetMapping("/all-cars")
  public List<CarDto> getAllCars(@RequestParam(value = "carStatus", required = false) String carStatus) {
    return rentalService.getAllCars(carStatus);
  }

}