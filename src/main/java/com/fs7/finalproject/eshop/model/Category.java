package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CATEGORIES", uniqueConstraints = {
        @UniqueConstraint(name = "IX_CATEGORIES_NAME", columnNames = "NAME")})
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

  @CreatedDate
  @Column(name = "CR_TIME", nullable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date crTime;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_CATEGORIES_USERS_CR_USER_ID"))
  private User crUser;

  @LastModifiedDate
  @Column(name = "LM_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lmTime;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_CATEGORIES_USERS_LM_USER_ID"))
  private User lmUser;
}
