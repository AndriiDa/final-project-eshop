package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "BRANDS", uniqueConstraints = {
        @UniqueConstraint(name = "IX_BRANDS_NAME", columnNames = "NAME")})
public class Brand extends AbstractEntityWithAudit{
  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  @Column(name = "NOTES", length = 1000)
  private String notes;

  @Column(name = "IS_ACTIVE", nullable = false)
  @ColumnDefault("true")
  private Boolean isActive;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })

  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_BRANDS_USERS_CR_USER_ID"))
  private User crUser;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })

  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_BRANDS_USERS_LM_USER_ID"))
  private User lmUser;
}