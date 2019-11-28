package com.my.springboot.account.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AccountSearchVo {

    /**转出明细id*/
    private Integer accOutDetailId;

    private String outUsers;

    private Date startDate;

    private Date endDate;
}
