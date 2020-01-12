package com.fs7.finalproject.eshop.model.dto;

import com.fs7.finalproject.eshop.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderItemDto extends AbstractDto{
  @Id
  private Long id;

  private Long orderId;

  private Long productId;

  private int quantity;

  private BigDecimal price;

  private BigDecimal discount;

  private String status;

  private Date crTime;

  private Long crUserId;

  private Long lmUserId;
}
