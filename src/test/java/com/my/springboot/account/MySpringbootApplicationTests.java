package com.my.springboot.account;

import com.my.springboot.account.dto.AccountOutDetail;
import com.my.springboot.account.repo.AccountOutDetailMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class MySpringbootApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccountOutDetailMapper accountOutDetailMapper;

    @Test
    void contextLoads() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from account");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getById(){

        AccountOutDetail accountOutDetail = accountOutDetailMapper.selectAccountOutDetail(1);
        System.out.println(accountOutDetail);
    }

}
