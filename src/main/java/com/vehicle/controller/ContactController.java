package com.vehicle.controller;

import com.vehicle.service.ContactService;
import com.vehicle.dto.ContactDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

  private final ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping(value = "/all")
  public List<ContactDto> allContacts() {
    return contactService.getAllContacts();
  }

}
