package com.jpivot.springbootdemo.service;

import com.jpivot.springbootdemo.model.CustomerInfo;

public interface CustomerInfoService {
    CustomerInfo getById(Integer id);
}
