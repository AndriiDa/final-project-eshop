package com.fs7.finalproject.eshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "ORDER_ITEMS")
public class OrderItem extends AbstractEntity{
  @Column(name = "ID", updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_ORDER_ITEMS_ORDERS_ORDER_ID"))
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
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
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date crTime;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "CR_USER_ID", referencedColumnName = "ID", nullable = false,
          foreignKey = @ForeignKey(name = "FK_ORDER_ITEMS_USERS_CR_USER_ID"))
  private User crUser;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "LM_USER_ID", referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_ORDER_ITEMS_USERS_LM_USER_ID"))
  private User lmUser;
}
