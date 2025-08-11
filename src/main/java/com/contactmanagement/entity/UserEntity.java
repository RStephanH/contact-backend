package com.contactmanagement.entity;

import jakarta.persistence.*;
/**
 * UserEntity
 */
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GeneratedType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String mail;

  //Constructor

  public UserEntity(){

  }

  public UserEntity(String username, String password, String mail){
    this.username=username;
    this.password=password;
    this.mail=mail;
  }

  // Getters and setters
  public Long getId() { return this.id; }

  public String getUsername() { return this.username; }
  public void setUsername(String username) { this.username = username; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getMail() { return this.mail; }
  public void setMail(String mail) { this.mail = mail; }
}
