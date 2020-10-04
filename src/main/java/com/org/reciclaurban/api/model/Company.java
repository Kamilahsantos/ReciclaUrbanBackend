package com.org.reciclaurban.api.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "company")
public class Company {

  private Long id;

  public Company() {
  }

  private String name;
  private String address;
  private String telephone;
  private String score;
  private String username;
  private String password;
  private String email;

  @Column(name = "password", length = 255, nullable = false)
  @NotNull(message = "Please input a password")
  @Size(max = 255)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }






  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "name", length = 255, nullable = false)
  @NotNull(message = "Please input a name")
  @Size(max = 255)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "address", length = 255, nullable = false)
  @NotNull(message = "Please input a address")
  @Size(max = 255)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "telephone", length = 255, nullable = false)
  @NotNull(message = "Please input a telephone")
  @Size(max = 255)
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Column(name = "score", length = 255, nullable = false)
  @NotNull(message = "Please input a score")
  @Size(max = 255)
  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  @Column(name = "username", length = 255, nullable = false)
  @NotNull(message = "Please input a username")
  @Size(max = 255)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "email", length = 255, nullable = false)
  @NotNull(message = "Please input a email")
  @Size(max = 255)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public Company(Long id, String name, String address, String telephone, String score, String username, String email, String password) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.telephone = telephone;
    this.score = score;
    this.username = username;
    this.email = email;
    this.password =password;
  }

  @Override
  public String toString() {
    return "Company [id=" + id + ", name=" + name + ", address=" + address + ", telephone=" + telephone + ", score=" +score + ", username=" +username
      + ", password=" + password    + "]";
  }


}
