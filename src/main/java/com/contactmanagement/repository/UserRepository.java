package com.contactmanagement.repository;

import com.contactmanagement.entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserRepository {

    @PersistenceContext(unitName = "contactPU") // persistence-unit defined in the persistence.xml
    private EntityManager em;

    public UserEntity findById(Long id) {
        return em.find(UserEntity.class, id);
    }

    public void save(UserEntity user) {
        em.persist(user);
    }

    public UserEntity update(UserEntity user) {
        return em.merge(user);
    }

    public void delete(Long id) {
        UserEntity user = findById(id);
        if (user != null) {
            em.remove(user);
        }
    }
}

