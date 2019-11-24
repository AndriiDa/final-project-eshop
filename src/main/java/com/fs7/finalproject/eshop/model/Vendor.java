package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
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
@Table(name = "VENDORS", uniqueConstraints = {
        @UniqueConstraint(name = "IX_VENDORS_NAME", columnNames = "NAME")})
public class Vendor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  //  @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY,
  //          cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  //  private List<Product> products;

  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  @Column(name = "NOTES", length = 1000)
  private String notes;

  @Column(name = "IS_ACTIVE", nullable = false)
  @ColumnDefault("true")
  private Boolean isActive;

  @Column(name = "CR_TIME", nullable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date crTime;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_VENDORS_USERS_CR_USER_ID"))
  private User crUser;

  @LastModifiedDate
  @Column(name = "LM_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lmTime;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_VENDORS_USERS_LM_USER_ID"))
  private User lmUser;
}