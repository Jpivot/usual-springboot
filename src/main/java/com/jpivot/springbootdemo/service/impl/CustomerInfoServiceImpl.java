package com.jpivot.springbootdemo.service.impl;

import com.jpivot.springbootdemo.mapper.CustomerInfoMapper;
import com.jpivot.springbootdemo.model.CustomerInfo;
import com.jpivot.springbootdemo.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {


    @Autowired
    CustomerInfoMapper customerInfoMapper;

    @Override
    public CustomerInfo getById(Integer id) {
        return customerInfoMapper.getById(id);
    }
}
