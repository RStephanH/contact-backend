package com.contactmanagement.repository;

import com.contactmanagement.entity.ContactEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Stateless
public class ContactRepository {

    @PersistenceContext(unitName = "contactPU") 
    private EntityManager em;

    public Optional<ContactEntity> findById(Long id) {
        return Optional.ofNullable(em.find(ContactEntity.class, id));
    }

    public void save(ContactEntity contact) {
        em.persist(contact);
    }

    public ContactEntity update(ContactEntity contact) {
        return em.merge(contact);
    }

    public boolean delete(Long id) {
    ContactEntity contact = em.find(ContactEntity.class, id);
    if (contact != null) {
        em.remove(contact);
        return true;
    }
    return false;
}

    public List<ContactEntity> findByUserId(String userId) {
      return em.createQuery(
              "SELECT c FROM ContactEntity c WHERE c.user.id = :userId", ContactEntity.class)
              .setParameter("userId", Long.parseLong(userId))
              .getResultList();
    }


    public List<ContactEntity> findAll() {
        return em.createQuery("SELECT c FROM ContactEntity c", ContactEntity.class)
                 .getResultList();
    }
}

