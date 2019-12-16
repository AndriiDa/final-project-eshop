package com.fs7.finalproject.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCTS")
@EqualsAndHashCode(callSuper = false)
public class Product extends AbstractEntityWithAudit{
  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "SKU_CODE", length = 50)
  private String skuCode;

  @Column(name = "TITLE", nullable = false, length = 200)
  private String title;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_PRODUCTS_CATEGORIES_CATEGORY_ID"))
  private Category category;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "VENDOR_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_PRODUCTS_VENDORS_VENDOR_ID"))
  private Vendor vendor;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_PRODUCTS_BRANDS_BRAND_ID"))
  private Brand brand;

  @Column(name = "DESCRIPTION", length = 1000)
  private String description;

  @Column(name = "CART_DESCRIPTION", length = 1000)
  private String cartDescription;

  @Column(name = "SHORT_DESCRIPTION", length = 1000)
  private String shortDescription;

  @Column(name = "LONG_DESCRIPTION", length = 1000)
  private String longDescription;

  @Column(name = "URL_THUMB", length = 100)
  private String urlThumb;

  @Column(name = "URL_IMG", length = 100)
  private String urlImg;

  @Column(name = "WEIGHT")
  private Double weight;

  @Column(name = "QUANTITY", nullable = false)
  private int quantity;

  @Column(name = "BASE_PRICE", nullable = false)
  @ColumnDefault("0.00")
  private BigDecimal basePrice;

  @Column(name = "DISCOUNT_PRICE")
  @ColumnDefault("0.00")
  private BigDecimal discountPrice;

  @Column(name = "IS_OFFER", nullable = false)
  @ColumnDefault("false")
  private boolean isOffer;

  @Column(name = "IS_RECOMMENDED", nullable = false)
  @ColumnDefault("false")
  private boolean isRecommended;

  @Column(name = "IS_ACTIVE", nullable = false)
  @ColumnDefault("true")
  private Boolean isActive;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_PRODUCTS_USERS_CR_USER_ID"))
  private User crUser;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_PRODUCTS_USERS_LM_USER_ID"))
  private User lmUser;

}