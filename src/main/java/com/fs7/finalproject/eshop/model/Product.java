package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.ForeignKey;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PRODUCTS")
@Data
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "CATEGORY_ID", nullable = false)
  private Long categoryId;

  @Column(name = "VENDOR_ID", nullable = false)
  private Long vendorId;

  @Column(name = "SKU_CODE", nullable = true, length = 50)
  private String skuCode;

  @Column(name = "TITLE", nullable = false, length = 200)
  private String title;

  @Column(name = "BRAND_ID", nullable = true)
  private int brandId;

  @Column(name = "DESCRIPTION", nullable = true, length = 1000)
  private String description;

  @Column(name = "CART_DESCRIPTION", nullable = true, length = 1000)
  private String cartDescription;

  @Column(name = "SHORT_DESCRIPTION", nullable = true, length = 1000)
  private String shortDescription;

  @Column(name = "LONG_DESCRIPTION", nullable = true, length = 1000)
  private String longDescription;

  @Column(name = "URL_THUMB", nullable = true, length = 100)
  private String urlThumb;

  @Column(name = "URL_IMG", nullable = true, length = 100)
  private String urlImg;

  @Column(name = "WEIGHT", nullable = true)
  private Double weight;

  @Column(name = "QUANTITY", nullable = false)
  private int quantity;

  @Column(name = "BASE_PRICE", nullable = true)
  private BigDecimal basePrice;

  @Column(name = "DISCOUNT_PRICE", nullable = true)
  private BigDecimal discountPrice;

  @Column(name = "IS_OFFER", nullable = false)
  private boolean isOffer;

  @Column(name = "IS_RECOMMEND", nullable = false)
  private boolean isRecommend;

  @Column(name = "IS_ACTIVE", nullable = false)
  private boolean isActive;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CR_TIME", nullable = false)
  private Date crTime;

  //  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  //  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
  //      foreignKey = @ForeignKey(name = "PRODUCT_CR_USER_ID"))
  //  private Collection<User> crUsers;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_PRODUCTS_USERS_CR_USER_ID"))
  private User crUser;


  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "LM_TIME")
  private Date lmTime;

  //  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  //  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
  //      foreignKey = @ForeignKey(name = "PRODUCT_LM_USER_ID"))
  //  private Collection<User> lmUsers;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
      foreignKey = @ForeignKey(name = "FK_PRODUCTS_USERS_LM_USER_ID"))
  private User lmUser;


}