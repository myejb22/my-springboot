package com.my.springboot.account.repo;

import com.my.springboot.account.dto.AccountOutDetail;
import com.my.springboot.account.vo.AccountSearchVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface AccountOutDetailMapper{

    AccountOutDetail selectAccountOutDetail(Integer id);

    Collection<AccountOutDetail> selectList(AccountSearchVo searchVo);
}
