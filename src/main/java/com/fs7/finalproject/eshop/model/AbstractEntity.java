package com.fs7.finalproject.eshop.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  //@Column(name = "CR_TIME", updatable = false)
  //public LocalDateTime crTime;

  //@Column(name = "LM_TIME", insertable = false)
  //public LocalDateTime lmTime;

  // @PrePersist
  //public void toCreate() {
  //  setCrTime(LocalDateTime.now());
  //}

  //@PreUpdate
  //public void toUpdate() {
  //  setLmTime(LocalDateTime.now());
  //}
}
