package com.rental.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CarCurrentStatusEntity {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "car_id", nullable = false)
  private Long carId;

  @Column(name = "status_id", nullable = false)
  private Long statusId;
}
