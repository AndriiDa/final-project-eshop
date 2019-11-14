package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Data
@Table(name = "BRANDS", uniqueConstraints = {
        @UniqueConstraint(name = "IX_BRANDS_NAME", columnNames = "NAME")})
public class Brand {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  @Column(name = "NOTES", length = 1000)
  private String notes;

  @Column(name = "IS_ACTIVE", nullable = false)
  @ColumnDefault("true")
  private Boolean isActive;

  @CreationTimestamp
  @Column(name = "CR_TIME", nullable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date crTime;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_BRANDS_USERS_CR_USER_ID"))
  private User crUser;

  @LastModifiedDate
  @Column(name = "LM_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lmTime;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_BRANDS_USERS_LM_USER_ID"))
  private User lmUser;
}