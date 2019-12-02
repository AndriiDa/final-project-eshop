package com.fs7.finalproject.eshop.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"crTime", "lmTime"},
    allowGetters = true
)
public abstract class AuditModel implements Serializable {
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CR_TIME", nullable = false, updatable = false)
  @CreatedDate
  private Date crTime;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "LM_TIME", nullable = false)
  @LastModifiedDate
  private Date lmTime;

}
