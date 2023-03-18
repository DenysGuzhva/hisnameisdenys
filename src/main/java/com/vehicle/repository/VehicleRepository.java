package com.vehicle.repository;

import com.vehicle.entity.VehicleEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository {

  private final List<VehicleEntity> allVehicles = new ArrayList<>();

  public void createProducts() {
    allVehicles.add(new VehicleEntity(1, 1, "Mercedes"));
    allVehicles.add(new VehicleEntity(2, 1, "Mercedes"));
    allVehicles.add(new VehicleEntity(3, 1, "Porsche"));
    allVehicles.add(new VehicleEntity(4, 2, "Mercedes"));
    allVehicles.add(new VehicleEntity(5, 2, "Mercedes"));
    allVehicles.add(new VehicleEntity(6, 3, "Mercedes"));
    allVehicles.add(new VehicleEntity(7, 3, "MG"));
    allVehicles.add(new VehicleEntity(8, 4, "Tesla"));
    allVehicles.add(new VehicleEntity(9, 5, "Lada"));
  }

  public List<VehicleEntity> getAllVehicles() {
    return allVehicles;
  }

  public void save(VehicleEntity vehicle) {
    if (vehicle.getOwnerId() < 0 || vehicle.getModel().isEmpty()) {
      throw new IllegalArgumentException();
    }
    vehicle.setId(allVehicles.size());
    allVehicles.add(vehicle);
  }

  public void saveAll(List<VehicleEntity> vehicles) {
    vehicles.forEach(this::save);
  }

  public VehicleEntity deleteById(int id) {
    allVehicles.removeIf(x -> x.getId() == (id));
    return null;
  }

  public List<VehicleEntity> findAllByOwnerId(int ownerId) {
    if (ownerId < 0) {
      return null;
    }
    return allVehicles.stream().filter(vehicle -> vehicle.getOwnerId() == (ownerId)).collect(Collectors.toList());
  }

}
