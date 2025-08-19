package com.contactmanagement.service;

import com.contactmanagement.dto.ContactDTO;
import com.contactmanagement.entity.ContactEntity;
import com.contactmanagement.entity.UserEntity;
import com.contactmanagement.mapper.ContactMapper;
import com.contactmanagement.repository.ContactRepository;
import com.contactmanagement.repository.UserRepository;

import java.util.stream.Collectors;
import java.util.List;
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

}

