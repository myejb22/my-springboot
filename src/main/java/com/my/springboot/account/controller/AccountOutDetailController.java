package com.my.springboot.account.controller;

import com.my.springboot.account.dto.AccountOutDetail;
import com.my.springboot.account.repo.AccountOutDetailMapper;
import com.my.springboot.account.vo.AccountSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/account/out/detail")
@RestController
public class AccountOutDetailController {
    @Autowired
    private AccountOutDetailMapper accountOutDetailMapper;

    @GetMapping(value = "/list")
    public AccountOutDetail list(AccountSearchVo searchVo, Model model){
        return accountOutDetailMapper.selectAccountOutDetail(searchVo.getAccOutDetailId());
    }
}
