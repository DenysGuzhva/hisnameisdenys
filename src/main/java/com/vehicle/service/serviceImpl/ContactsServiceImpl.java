package com.vehicle.service.serviceImpl;

import com.vehicle.dto.ContactDto;
import com.vehicle.mapper.Mapper;
import com.vehicle.repository.ContactRepository;
import com.vehicle.service.ContactService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContactsServiceImpl implements ContactService {

  private final ContactRepository contactRepository;
  private final Mapper mapper;

  public ContactsServiceImpl(ContactRepository contactRepository, Mapper mapper) {
    this.contactRepository = contactRepository;
    this.mapper = mapper;
  }

  @Override
  public List<ContactDto> getAllContacts() {
    return mapper.allToDto(contactRepository.getAllContacts());
  }

}
