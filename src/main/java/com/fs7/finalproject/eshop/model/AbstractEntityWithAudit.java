package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"crTime", "lmTime"}, allowGetters = true)
public abstract class AbstractEntityWithAudit extends AbstractEntity {
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CR_TIME", nullable = false, updatable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
  @CreatedDate
  public Date crTime;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "LM_TIME", insertable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
  @LastModifiedDate
  public Date lmTime;

  // @PrePersist
  //public void toCreate() {
  //  setCrTime(LocalDateTime.now());
  //}

  //@PreUpdate
  //public void toUpdate() {
  //  setLmTime(LocalDateTime.now());
  //}
}
