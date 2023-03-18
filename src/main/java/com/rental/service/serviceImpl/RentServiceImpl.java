package com.rental.service.serviceImpl;

import static com.rental.utils.Utils.ACTIVE_STATUS_ID;
import static com.rental.utils.Utils.EXCLUDED_PERMANENTLY_STATUS_ID;
import static com.rental.utils.Utils.EXCLUDED_STATUS;
import static com.rental.utils.Utils.EXCLUDED_STATUS_ID;
import static com.rental.utils.Utils.FREE_STATUS;
import static com.rental.utils.Utils.FREE_STATUS_ID;
import static com.rental.utils.Utils.TAKEN_STATUS;
import static com.rental.utils.Utils.TAKEN_STATUS_ID;
import com.rental.entity.CarCurrentStatusEntity;
import com.rental.repository.CarCurrentStatusRepository;
import com.rental.dto.CarDto;
import com.rental.entity.CarEntity;
import com.rental.mapper.CarMapper;
import com.rental.entity.CarRentHistoryEntity;
import com.rental.repository.CarRentHistoryRepository;
import com.rental.repository.CarRepository;
import com.rental.dto.CustomerDto;
import com.rental.entity.CustomerEntity;
import com.rental.repository.CustomerRepository;
import com.rental.service.RentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentServiceImpl implements RentService {

  private final CarRepository carRepository;
  private final CustomerRepository customerRepository;
  private final CarCurrentStatusRepository carCurrentStatusRepository;
  private final CarMapper carMapper;
  private final CarRentHistoryRepository carRentHistoryRepository;

  @Override
  public CarEntity addNewCar(CarDto carDto) {
    CarEntity savedCar = carRepository.save(carMapper.carDtoToEntity(carDto));
    if (savedCar != null) {
      carCurrentStatusRepository.save(new CarCurrentStatusEntity(null, savedCar.getId(), FREE_STATUS_ID));
    }
    return savedCar;
  }

  @Override
  public List<CarDto> getAllCars(String carStatus) {
    if (carStatus != null
        && (carStatus.equals(FREE_STATUS) || carStatus.equals(EXCLUDED_STATUS) || carStatus.equals(TAKEN_STATUS))) {
      return carMapper.allCarEntitiesToDtos(carRepository.getByStatus(carStatus));
    }
    return carMapper.allCarEntitiesToDtos(carRepository.findAll());
  }

  @Override
  public void excludeCar(Boolean isExclusionPermanent, Long carId) {
    if (isExclusionPermanent) {
      carCurrentStatusRepository.updateStatus(EXCLUDED_STATUS_ID, carId);
    }
    carCurrentStatusRepository.updateStatus(EXCLUDED_PERMANENTLY_STATUS_ID, carId);
  }

  @Override
  public void includeCar(Long carId) {
    CarCurrentStatusEntity byCarId = carCurrentStatusRepository.findByCarId(carId);
    if (byCarId != null && byCarId.getStatusId() != null && byCarId.getStatusId().equals(EXCLUDED_STATUS_ID)) {
      carCurrentStatusRepository.updateStatus(FREE_STATUS_ID, carId);
    }
  }


  @Override
  public void rentCar(CustomerDto customer, Long carId) {
    CustomerEntity customerEntity = checkThatExistOrCreateNewCustomer(customer);
    CarCurrentStatusEntity requestedCarStatus = carCurrentStatusRepository.findByCarId(carId);

    if (!isCustomerHaveActiveRents(customerEntity.getId())) {
      if (requestedCarStatus != null && requestedCarStatus.getStatusId().equals(FREE_STATUS_ID)) {
        carRentHistoryRepository.save(new CarRentHistoryEntity(null, carId, customerEntity.getId(), ACTIVE_STATUS_ID));
        carCurrentStatusRepository.updateStatus(TAKEN_STATUS_ID, carId);
      }

      if (requestedCarStatus == null) {
        throw new UnsupportedOperationException("No car with this id!");
      }
      if (requestedCarStatus.getStatusId().equals(EXCLUDED_STATUS_ID) || requestedCarStatus.getStatusId()
          .equals(EXCLUDED_PERMANENTLY_STATUS_ID)) {
        throw new UnsupportedOperationException("Requested car can't be rented! EXCLUDED!");
      }
    }

    throw new UnsupportedOperationException("Customer have active rents!");

  }

  @Override
  public void takeCarBack(Long customerId, Long carId) {
    CarRentHistoryEntity requestedCarHistory = carRentHistoryRepository.findByCustomerIdAndCarIdAndRentStatusId(
        customerId, carId, ACTIVE_STATUS_ID);

    if (requestedCarHistory != null) {
      carRentHistoryRepository.updateStatusToClosedById(requestedCarHistory.getId());
      carCurrentStatusRepository.updateStatus(FREE_STATUS_ID, carId);
    }

    throw new UnsupportedOperationException("Can't find rent by requested data!");

  }

  private Boolean isCustomerHaveActiveRents(Long customerId) {
    return carRentHistoryRepository.findByCustomerIdAndRentStatusId(customerId, ACTIVE_STATUS_ID) != null;
  }

  private CustomerEntity checkThatExistOrCreateNewCustomer(CustomerDto customer) {
    CustomerEntity byDriverLicenseId = customerRepository.findByDriverLicenseId(customer.getDriverLicenseId());
    if (byDriverLicenseId == null) {
      byDriverLicenseId = customerRepository.save(
          new CustomerEntity(null, customer.getFullName(), customer.getDriverLicenseId()));
    }
    return byDriverLicenseId;
  }

}
