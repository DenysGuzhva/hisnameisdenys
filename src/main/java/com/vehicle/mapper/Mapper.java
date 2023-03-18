package com.vehicle.mapper;

import com.vehicle.dto.ContactDto;
import com.vehicle.entity.ContactEntity;
import com.vehicle.repository.VehicleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

  private final VehicleRepository vehicleRepository;

  public Mapper(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  public ContactDto toDto(ContactEntity contact) {
    return ContactDto.builder()
        .id(contact.getId())
        .fullName(contact.getFullName())
        .vehicles(vehicleRepository.findAllByOwnerId(contact.getId()))
        .build();
  }

  public List<ContactDto> allToDto(List<ContactEntity> contacts) {
    return contacts.stream().map(this::toDto).collect(Collectors.toList());
  }

}
