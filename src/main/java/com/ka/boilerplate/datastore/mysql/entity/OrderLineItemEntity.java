package com.ka.boilerplate.datastore.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "order_line_items")
public class OrderLineItemEntity {

  @Id
  @Column(name = "order_product_id")
  int orderProductId;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = OrderEntity.class)
  @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "order_id")
  OrderEntity order;

  @Column(name = "product_id")
  int productId;

  @Column(name = "name")
  @Size(max = 255)
  String name;

  @Column(name = "quantity")
  int quantity;

  @Column(name = "price")
  double price;

  public OrderLineItemEntity() {
    super();
  }

  public OrderLineItemEntity(int orderProductId, int productId, String name, int quantity,
      double price) {
    super();
    this.orderProductId = orderProductId;
    this.productId = productId;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  public int getOrderProductId() {
    return orderProductId;
  }

  public void setOrderProductId(int orderProductId) {
    this.orderProductId = orderProductId;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public OrderEntity getOrder() {
    return order;
  }

  public void setOrder(OrderEntity order) {
    this.order = order;
  }
}
