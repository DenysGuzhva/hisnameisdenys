package com.rental.repository;

import com.rental.entity.CustomerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  @Override
  List<CustomerEntity> findAll();

  CustomerEntity findByDriverLicenseId(String driverLicenseId);
}
