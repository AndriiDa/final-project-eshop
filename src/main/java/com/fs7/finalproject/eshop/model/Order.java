package com.fs7.finalproject.eshop.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "ORDERS")
public class Order extends AbstractEntityWithAudit{
  @Column(name = "ID", updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_ORDERS_USERS_USER_ID"))
  private User user;

  @Column(name = "ORDER_NUM", nullable = false, length = 20)
  private String orderNum;

  @Temporal(TemporalType.DATE)
  @Column(name = "ORDER_DATE", nullable = false)
  private Date orderDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "REQUIRED_DATE")
  private Date requiredDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "SHIPPED_DATE")
  private Date shippedDate;

  @Column(name = "RECEIPT_NAME", length = 100)
  private String receiptName;

  @Column(name = "RECEIPT_PHONE", length = 20)
  private String receiptPhone;

  @OneToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "RECEIPT_ADDRESS_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_ORDERS_ADDRESSES_RECEIPT_ADDRESS_ID"))
  private Address receiptAddress;

  @Column(name = "TRACKING_NUMBER", length = 50)
  private String trackingNumber;

  @Column(name = "NOTES", length = 1000)
  private String notes;

  //Make Calculate from Items
  //, precision = 9, scale = 2
  @Column(name = "TOTAL",  nullable = false)
  @ColumnDefault("0.00")
  private BigDecimal total;

  @Column(name = "STATUS", columnDefinition = "CHAR", nullable = false)
  private String status;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_ORDERS_USERS_CR_USER_ID"))
  private User crUser;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_ORDERS_USERS_LM_USER_ID"))
  private User lmUser;
}
