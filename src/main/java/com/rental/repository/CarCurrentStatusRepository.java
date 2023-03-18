package com.rental.repository;

import com.rental.entity.CarCurrentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCurrentStatusRepository extends JpaRepository<CarCurrentStatusEntity, Long> {

  @Query(value = "UPDATE car_current_status "
      + "SET status_id = :newStatusId "
      + "WHERE car_id = :carId", nativeQuery = true)
  void updateStatus(Long newStatusId, Long carId);

  CarCurrentStatusEntity findByCarId(Long carId);


}
