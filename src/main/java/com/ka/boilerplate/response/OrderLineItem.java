package com.ka.boilerplate.response;

import com.ka.boilerplate.datastore.mysql.entity.OrderLineItemEntity;

public class OrderLineItem {

  int orderProductId;

  int productId;

  String name;

  int quantity;

  double price;

  public OrderLineItem() {
    super();
  }

  public OrderLineItem(int orderProductId, int productId, String name, int quantity, double price) {
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

  public static OrderLineItem fromOrderLineItemEntity(OrderLineItemEntity oLIE) {
    return new OrderLineItem(oLIE.getOrderProductId(), oLIE.getProductId(), oLIE.getName(),
        oLIE.getQuantity(), oLIE.getPrice());
  }

  @Override
  public String toString() {
    return "{ orderProductId=" + orderProductId + ", productId=" + productId + ", name=" + name
        + ", quantity=" + quantity + ", price=" + price + "}";
  }
}
