package com.vehicle.dto;

import com.vehicle.entity.VehicleEntity;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDto {

  private int id;
  private String fullName;
  private List<VehicleEntity> vehicles;

}
