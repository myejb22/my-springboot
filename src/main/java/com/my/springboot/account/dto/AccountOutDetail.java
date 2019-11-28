package com.my.springboot.account.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountOutDetail extends BaseDto{

    /**转出金额*/
    private BigDecimal outFunds;

    /**转出给谁*/
    private String outUsers;

    /**转账详细信息*/
    private String remark;
}
