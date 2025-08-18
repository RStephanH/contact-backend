package com.contactmanagement.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class ContactEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contact_id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    // Relation avec UserEntity (clé étrangère user_id)
    @ManyToOne(fetch = FetchType.LAZY) // chaque contact appartient à un user
    @JoinColumn(name = "user_id", nullable = false) // FK vers la table users
    private UserEntity user;

    // Constructeurs
    public ContactEntity() {}

    public ContactEntity(String firstName, String lastName, String email, String phone, UserEntity user) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.phone = phone;
        this.user = user;
    }

    // Getters & Setters
    public String getId() {
        return Long.toString(this.contact_id);
    }

    public String getFirstName() { return first_name; }
    public void setFirstName(String first_name) { this.first_name = first_name; }

    public String getLastName() { return last_name; }
    public void setLastName(String last_name) { this.last_name = last_name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
}

