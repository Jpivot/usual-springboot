package com.jpivot.springbootdemo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sherlock on 2019/3/3.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerInfo{

    private Integer id;

    private String username;

    private String interest;

}
