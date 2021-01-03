package com.jpivot.springbootdemo.controller;

import com.jpivot.springbootdemo.mapper.CustomerInfoMapper;
import com.jpivot.springbootdemo.model.CustomerInfo;
import com.jpivot.springbootdemo.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock on 2019/3/3.
 */
@RestController
public class HelloWorldController {
    private static Logger logger= LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    CustomerInfoService customerInfoService;

    @RequestMapping("/customerinfo")
    public CustomerInfo getUser(Integer id, HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        Thread.sleep(5000);
        CustomerInfo customerInfo=customerInfoService.getById(id);
        HttpSession httpSession = request.getSession();
        logger.info(JSON.toJSONString(customerInfo));
        return customerInfo;
    }


    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";

    }

}
