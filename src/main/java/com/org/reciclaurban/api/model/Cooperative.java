package com.org.reciclaurban.api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "cooperative")
public class Cooperative {

  private Long id;
  private String name;
  private String telephone;
  private String site;
  private String category;
  private String address;
  private LocalDateTime created_at;

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

  @Column(name = "telephone", length = 255, nullable = false)
  @NotNull(message = "Please input a telephone")
  @Size(max = 255)
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Column(name = "site", length = 255, nullable = false)
  @NotNull(message = "Please input a site")
  @Size(max = 255)
  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  @Column(name = "category", length = 255, nullable = false)
  @NotNull(message = "Please input a category")
  @Size(max = 255)
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
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

  @Column(name = "created_at", updatable = false)
  @CreatedDate
  @CreationTimestamp
  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  public Cooperative() {
  }

  public Cooperative(Long id, String name, String telephone, String site, String category, String address, LocalDateTime created_at) {
    this.id = id;
    this.name = name;
    this.telephone = telephone;
    this.site = site;
    this.category = category;
    this.address = address;
    this.created_at = created_at;
  }


  @Override
  public String toString() {
    return "Cooperative [id=" + id + ", name=" + name + ", telephone=" + telephone + ", site=" + site + ", category=" +category + ", address=" +address
      + ", created_at=" + created_at    + "]";
  }

}
