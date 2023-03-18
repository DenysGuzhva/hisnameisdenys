package com.vehicle.repository;

import com.vehicle.entity.ContactEntity;
import com.vehicle.entity.VehicleEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

  private final List<ContactEntity> allOwners = new ArrayList<>();

  public void createProducts() {
    allOwners.add(new ContactEntity(1, "andrii S"));
    allOwners.add(new ContactEntity(2, "Denis P"));
    allOwners.add(new ContactEntity(3, "Andrii K"));
    allOwners.add(new ContactEntity(4, "Taras B"));
    allOwners.add(new ContactEntity(5, "Maksym H"));
  }

  public List<ContactEntity> getAllContacts() {
    return allOwners;
  }

  public void save(ContactEntity contact) {
    contact.setId(allOwners.size());
    allOwners.add(contact);
  }

  public void saveAll(List<ContactEntity> vehicles) {
    vehicles.forEach(this::save);
  }

  public VehicleEntity deleteById(int id) {
    allOwners.removeIf(x -> x.getId() == (id));
    return null;
  }

}
