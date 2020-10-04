package com.org.reciclaurban.api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity")
public class Activity {

  private Long id;
  private Long userId;
  private String title;
  private String description;
  private String category;
  private String point;
  private LocalDateTime created_at;



  public Activity() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "userId", length = 255, nullable = false)
  @NotNull(message = "Please input a userId")
  @Size(max = 255)
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(name = "title", length = 255, nullable = false)
  @NotNull(message = "Please input a title")
  @Size(max = 255)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "description", length = 255, nullable = false)
  @NotNull(message = "Please input a description")
  @Size(max = 255)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  @Column(name = "point", length = 255, nullable = false)
  @NotNull(message = "Please input a point")
  @Size(max = 255)
  public String getPoint() {
    return point;
  }

  public void setPoint(String point) {
    this.point = point;
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

  public Activity(Long id, Long userId, String title, String description, String category, String point, LocalDateTime created_at) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.description = description;
    this.category = category;
    this.point = point;
    this.created_at = created_at;
  }

  @Override
  public String toString() {
    return "Activity [id=" + id + ", userId=" + userId + ", title=" + title + ", description=" + description + ", category=" +category + ", point=" +point
      + ", created_at=" + created_at    + "]";
  }


}
