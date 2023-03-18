package com.rental.mapper;

import com.rental.dto.CarDto;
import com.rental.entity.CarEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CarMapper {

  public CarDto carEntityToDto(CarEntity carEntity) {
    return new CarDto(carEntity.getModel(), carEntity.getRegistrationNumber());
  }

  public CarEntity carDtoToEntity(CarDto carDto) {
    return new CarEntity(null, carDto.getModel(), carDto.getRegistrationNumber());
  }

  public List<CarDto> allCarEntitiesToDtos(List<CarEntity> entities) {
    return entities.stream().map(this::carEntityToDto).collect(Collectors.toList());
  }

}
