package com.ka.boilerplate.datastore.mysql.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {

  private static final long serialVersionUID = -9214298094816320692L;

  @Id
  @Column(name = "order_id")
  int orderId;

  @Column(name = "total")
  double total;

  @Column(name = "date_added")
  Timestamp dateAdded;

  @Column(name = "date_modified")
  Timestamp dateModified;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
  private List<OrderLineItemEntity> items = new ArrayList<OrderLineItemEntity>();

  public OrderEntity() {
    super();
  }

  public OrderEntity(int orderId, double total, Timestamp dateAdded, Timestamp dateModified) {
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

  public List<OrderLineItemEntity> getItems() {
    return items;
  }

  public void setItems(List<OrderLineItemEntity> items) {
    this.items = items;
  }

}
