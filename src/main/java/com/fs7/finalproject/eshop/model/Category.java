package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CATEGORIES", uniqueConstraints = {
        @UniqueConstraint(name = "IX_CATEGORIES_NAME", columnNames = "NAME")})
@EqualsAndHashCode(callSuper = false)
public class Category extends AbstractEntityWithAudit{
  @Column(name = "ID", updatable = false)
  private Long id;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_CATEGORIES_CATEGORIES_PARENT_ID"))
  @JsonBackReference
  private Category parentCategory;

  @Column(name = "IS_GROUP", nullable = false)
  private Boolean isGroup;

  @Column(name = "CODE", length = 50)
  private String code;

  @Column(name = "NAME", nullable = false, length = 200)
  private String name;

  @Column(name = "IMG_URL", length = 100)
  private String imgUrl;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;

  @Column(name = "IS_ACTIVE", nullable = false)
  @ColumnDefault("true")
  private Boolean isActive;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_CATEGORIES_USERS_CR_USER_ID"))
  private User crUser;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_CATEGORIES_USERS_LM_USER_ID"))
  private User lmUser;
}
