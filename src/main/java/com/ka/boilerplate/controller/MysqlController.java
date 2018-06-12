package com.ka.boilerplate.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ka.boilerplate.exception.AppException;
import com.ka.boilerplate.response.Order;
import com.ka.boilerplate.response.ServiceResponse;
import com.ka.boilerplate.service.MysqlService;

@RestController
@RequestMapping(value = "/orders")
public class MysqlController {

  private static final Logger logger = LoggerFactory.getLogger(MysqlController.class);

  @Autowired
  MysqlService mysqlService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ServiceResponse<List<Integer>> get(HttpServletRequest servletReq) throws AppException {
    return new ServiceResponse<List<Integer>>(mysqlService.getList(), HttpStatus.OK);
  }

  @RequestMapping(value = "/save", method = RequestMethod.GET)
  public ServiceResponse<Order> save(HttpServletRequest servletReq) throws AppException {
    return new ServiceResponse<Order>(mysqlService.save(), HttpStatus.OK);
  }
}
