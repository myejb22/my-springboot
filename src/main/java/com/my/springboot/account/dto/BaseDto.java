package com.my.springboot.account.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDto {

    private Integer id;

    private Date createTime;

    private Date modifyTime;
}
