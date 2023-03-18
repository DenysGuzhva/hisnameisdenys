package com.rental.repository;

import com.rental.entity.CarRentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentHistoryRepository extends JpaRepository<CarRentHistoryEntity, Long> {

  CarRentHistoryEntity findByCustomerIdAndCarIdAndRentStatusId(Long customerId, Long carId, Long statusId);

  CarRentHistoryEntity findByCustomerIdAndRentStatusId(Long customerId, Long rentStatusId);

  @Query(value = "UPDATE car_rent_history "
      + "SET rent_status_id = 1 "
      + "WHERE id = :rentHistoryId", nativeQuery = true)
  void updateStatusToClosedById(Long rentHistoryId);


}
