package com.contactmanagement.mapper;

import com.contactmanagement.entity.ContactEntity;
import com.contactmanagement.entity.UserEntity;
import com.contactmanagement.dto.ContactDTO;

public class ContactMapper {

    public static ContactDTO toDto(ContactEntity entity) {
        return new ContactDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                Long.toString(entity.getUser().getId())
        );
    }

    public static ContactEntity toEntity(ContactDTO dto, UserEntity userEntity) {
        return new ContactEntity(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhone(),
                userEntity
        );
    }
}

