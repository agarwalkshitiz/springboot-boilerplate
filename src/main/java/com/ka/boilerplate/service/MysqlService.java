package com.ka.boilerplate.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ka.boilerplate.datastore.mysql.entity.OrderEntity;
import com.ka.boilerplate.datastore.mysql.entity.OrderLineItemEntity;
import com.ka.boilerplate.datastore.mysql.repo.OrderRepo;
import com.ka.boilerplate.response.Order;

@Service
public class MysqlService {
  private static final Logger logger = LoggerFactory.getLogger(MysqlService.class);

  @Autowired
  OrderRepo orderRepo;

  public List<Integer> getList() {
    return new ArrayList<Integer>();
  }

  public Order save() {
    int seed = new Random().nextInt(100);

    OrderEntity oE = new OrderEntity(seed, seed * 10, new Timestamp(new Date().getTime()),
        new Timestamp(new Date().getTime() + 3600000));
    List<OrderLineItemEntity> items = new ArrayList<OrderLineItemEntity>();
    OrderLineItemEntity oLIE =
        new OrderLineItemEntity(seed * 100, seed * 1000, "Name" + seed, seed * 10000, seed * 10000);
    oLIE.setOrder(oE);
    items.add(oLIE);
    oE.setItems(items);
    orderRepo.save(oE);

    return Order.fromOrderEntity(oE);
  }
}
