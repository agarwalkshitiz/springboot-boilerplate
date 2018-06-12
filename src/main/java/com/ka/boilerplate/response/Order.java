package com.ka.boilerplate.response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.ka.boilerplate.datastore.mysql.entity.OrderEntity;

public class Order {

  int orderId;

  double total;

  Timestamp dateAdded;

  Timestamp dateModified;

  private List<OrderLineItem> items = new ArrayList<OrderLineItem>();

  public Order() {
    super();
  }

  public Order(int orderId, double total, Timestamp dateAdded, Timestamp dateModified) {
    super();
    this.orderId = orderId;
    this.total = total;
    this.dateAdded = dateAdded;
    this.dateModified = dateModified;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Timestamp getDateAdded() {
    return dateAdded;
  }

  public void setDateAdded(Timestamp dateAdded) {
    this.dateAdded = dateAdded;
  }

  public Timestamp getDateModified() {
    return dateModified;
  }

  public void setDateModified(Timestamp dateModified) {
    this.dateModified = dateModified;
  }

  public List<OrderLineItem> getItems() {
    return items;
  }

  public void setItems(List<OrderLineItem> items) {
    this.items = items;
  }

  public static Order fromOrderEntity(OrderEntity orderEntity) {
    Order order = new Order(orderEntity.getOrderId(), orderEntity.getTotal(),
        orderEntity.getDateAdded(), orderEntity.getDateModified());
    List<OrderLineItem> lineItems = new ArrayList<OrderLineItem>();
    order.setItems(lineItems);
    orderEntity.getItems().forEach(orderLineItemEntity -> {
      lineItems.add(OrderLineItem.fromOrderLineItemEntity(orderLineItemEntity));
    });
    return order;
  }

  @Override
  public String toString() {
    return "Order [orderId=" + orderId + ", total=" + total + ", dateAdded=" + dateAdded
        + ", dateModified=" + dateModified + ", items=" + items + "]";
  }
}
