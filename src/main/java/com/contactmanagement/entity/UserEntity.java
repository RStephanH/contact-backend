package com.contactmanagement.entity;

import java.io.Serializable;

import jakarta.persistence.*;
/**
 * UserEntity
 */
@Entity
@Table(name = "users")
public class UserEntity implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long user_id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String mail;

  @Column (nullable = false)
  private String first_name;

  @Column (nullable =  false)
  private String last_name;

  //Constructor

  public UserEntity(){

  }

  public UserEntity(String username, String password, String mail, String first_name, String last_name){
    this.username=username;
    this.password=password;
    this.mail=mail;
    this.first_name=first_name;
    this.last_name=last_name;
  }

  // Getters and setters
  public Long getId() { return this.user_id; }

  public String getUsername() { return this.username; }
  public void setUsername(String username) { this.username = username; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getMail() { return this.mail; }
  public void setMail(String mail) { this.mail = mail; }
}
