package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDto extends AbstractDtoWithAudit {
  @Id
  private Long id;

  private Long categoryId;

  private String skuCode;

  private String title;

  private String description;

  private Long brandId;

  private Long vendorId;

  private String cartDescription;

  private String shortDescription;

  private String longDescription;

  private String urlThumb;

  private String urlImg;

  private Double weight;

  private int quantity;

  private BigDecimal basePrice;

  private BigDecimal discountPrice;

  private Boolean isOffer;

  private Boolean isRecommended;

  private Boolean isActive;

  private Long crUserId;

  private Long lmUserId;
}