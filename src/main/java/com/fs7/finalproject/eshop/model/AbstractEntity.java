package com.fs7.finalproject.eshop.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {
  @Id
  @GeneratedValue
  public Long id;

  @Column(name = "CR_TIME", updatable = false)
  public LocalDateTime crTime;

  @Column(name = "LM_TIME", insertable = false)
  public LocalDateTime lmTime;

  @PrePersist
  public void toCreate() {
    setCrTime(LocalDateTime.now());
  }

  @PreUpdate
  public void toUpdate() {
    setLmTime(LocalDateTime.now());
  }
}
