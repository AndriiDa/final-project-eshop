package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDto extends AbstractDtoWithAudit{
  @Id
  private Long id;

  private Long userId;

  private String orderNum;

  private Date orderDate;

  private Date requiredDate;

  private Date shippedDate;

  private String receiptName;

  private String receiptPhone;

  private Long receiptAddressId;

  private String trackingNumber;

  private String notes;

  private BigDecimal total;

  private String status;

  private Long crUserId;

  private Long lmUserId;
}
