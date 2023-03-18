package com.rental.repository;

import com.rental.entity.CarEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

  @Override
  <S extends CarEntity> S save(S entity);

  @Override
  Optional<CarEntity> findById(Long aLong);

  @Override
  List<CarEntity> findAll();

  @Query(value = "SELECT car_id, model, registration_number FROM car "
      + "LEFT JOIN car_current_status ccs on car.id = ccs.car_id "
      + "LEFT JOIN car_status cs on ccs.status_id = cs.id "
      + "WHERE cs.name = :status", nativeQuery = true)
  List<CarEntity> getByStatus(String status);

}
