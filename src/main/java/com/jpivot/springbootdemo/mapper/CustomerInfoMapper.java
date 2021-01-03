package com.jpivot.springbootdemo.mapper;

import java.util.List;
import com.jpivot.springbootdemo.model.CustomerInfo;
/**
 * Created by Sherlock on 2019/5/12.
 */
public interface CustomerInfoMapper {

    List<CustomerInfo> getLimitTen();

    CustomerInfo getById(Integer i);


}
