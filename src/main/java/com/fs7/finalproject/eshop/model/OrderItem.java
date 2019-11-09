package com.fs7.finalproject.eshop.model;

import lombok.Data;
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
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "ORDER_ITEMS")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_ORDER_ITEMS_ORDERS_ORDER_ID"))
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_ORDER_ITEMS_PRODUCTS_PRODUCT_ID"))
  private Product product;

  @Column(name = "QUANTITY", nullable = false)
  private int quantity;

  @Column(name = "PRICE",  nullable = false)
  @ColumnDefault("0.00")
  private BigDecimal price;

  @Column(name = "DISCOUNT",  nullable = false)
  @ColumnDefault("0.00")
  private BigDecimal discount;

  @Column(name = "STATUS", columnDefinition = "CHAR", nullable = false)
  private String status;

  @CreatedDate
  @Column(name = "CR_TIME", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date crTime;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_ORDER_ITEMS_USERS_CR_USER_ID"))
  private User crUser;

  @LastModifiedDate
  @Column(name = "LM_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lmTime;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_ORDER_ITEMS_USERS_LM_USER_ID"))
  private User lmUser;
}
