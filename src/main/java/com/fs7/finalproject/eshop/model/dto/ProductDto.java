package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDto {
  @Id
  private Long id;

  private Long categoryId;

  private Long vendorId;

  private String skuCode;

  private String title;

  private Long brandId;

  private String description;

  private String cartDescription;

  private String shortDescription;

  private String longDescription;

  private String urlThumb;

  private String urlImg;

  private Double weight;

  private int quantity;

  private BigDecimal basePrice;

  private BigDecimal discountPrice;

  private boolean isOffer;

  private boolean isRecommend;

  private Boolean isActive;

  private Date crTime;

  private Long crUserId;

  private Date lmTime;

  private Long lmUserId;
}