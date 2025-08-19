package com.contactmanagement.service;

import com.contactmanagement.dto.ContactDTO;
import com.contactmanagement.entity.ContactEntity;
import com.contactmanagement.entity.UserEntity;
import com.contactmanagement.mapper.ContactMapper;
import com.contactmanagement.repository.ContactRepository;
import com.contactmanagement.repository.UserRepository;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ContactService {

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private UserRepository userRepository;

    public ContactEntity createContact(ContactDTO dto) {
        // find the User by the ID
        UserEntity user = userRepository.findById(Long.parseLong(dto.getUserId()));
        if (user == null) {
            throw new IllegalArgumentException("Utilisateur introuvable avec ID " + dto.getUserId());
        }

        // Mapper DTO -> Entity
        ContactEntity entity = ContactMapper.toEntity(dto, user);

        // Persist
        contactRepository.save(entity);

        return entity;
    }

    public List<ContactDTO> getContactsByUserId(String userId) {
      List<ContactEntity> entities = contactRepository.findByUserId(userId);
      return entities.stream()
                     .map(e -> ContactMapper.toDto(e))
                     .collect(Collectors.toList());
    }

    public ContactDTO updateContact(Long id, ContactDTO updatedContactDTO) {
    UserEntity user = userRepository.findById(Long.parseLong(updatedContactDTO.getUserId()));
    ContactEntity updatedContact = ContactMapper.toEntity(updatedContactDTO, user);
        Optional<ContactEntity> existingContact = contactRepository
    .findById(id);

        if (existingContact.isPresent()) {
            ContactEntity contact = existingContact.get();

            // Update the fields
            contact.setFirstName(updatedContact.getFirstName());
            contact.setLastName(updatedContact.getLastName());
            contact.setPhone(updatedContact.getPhone());
            contact.setEmail(updatedContact.getEmail());

            return ContactMapper.toDto(contactRepository.update(contact)); // merge into DB
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        return contactRepository.delete(id);
        }
}

