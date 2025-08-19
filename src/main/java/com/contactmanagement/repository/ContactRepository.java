package com.contactmanagement.repository;

import com.contactmanagement.entity.ContactEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ContactRepository {

    @PersistenceContext(unitName = "contactPU") 
    private EntityManager em;

    public ContactEntity findById(Long id) {
        return em.find(ContactEntity.class, id);
    }

    public void save(ContactEntity contact) {
        em.persist(contact);
    }

    public ContactEntity update(ContactEntity contact) {
        return em.merge(contact);
    }

    public void delete(Long id) {
        ContactEntity contact = findById(id);
        if (contact != null) {
            em.remove(contact);
        }
    }

    public List<ContactEntity> findAll() {
        return em.createQuery("SELECT c FROM ContactEntity c", ContactEntity.class)
                 .getResultList();
    }
}

