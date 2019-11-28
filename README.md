##my-springboot项目

> my-springboot项目，是我学习springboot来创建的首个项目，只用于学习。同时也检验下自己对springboot掌握的情况。

#### 数据库的配置模块

- **`config`包下的`DruidConfig`**

  ```java
  package com.my.springboot.account.config;
  
  import com.alibaba.druid.filter.config.ConfigTools;
  import com.alibaba.druid.pool.DruidDataSource;
  import com.alibaba.druid.support.http.StatViewServlet;
  import com.alibaba.druid.support.http.WebStatFilter;
  
  import lombok.extern.slf4j.Slf4j;
  
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.boot.web.servlet.FilterRegistrationBean;
  import org.springframework.boot.web.servlet.ServletRegistrationBean;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  
  import javax.sql.DataSource;
  import java.sql.SQLException;
  
  /**
   * Druid配置类
   * 1、可以监控数据库访问性能，内置了插件：StatFilter，能够详细统计SQL执行性能，用于线上分析数据库访问性能。
   * 2、替换DBCP、C3P0数据库连接池，提供了一个高效稳定的扩展性能好的数据库连接池DruidDataSource。
   * 3、数据库加密，DruidDriuiver和DruidDataSource都支持PasswordCallback。
   * 4、SQL执行日志。
   * 5、扩展JDBC，如果你要对JDBC层有编程的需求，可以通过Druid提供的Filter-Chain机制，很方便编写JDBC层的扩展插件
   * Druid是一个阿里开源的数据库连接池框架，有可视化界面。
   * http://localhost:8080/druid/login.html
   * 可以查看sql访问速度等等很多信息，感觉还是比较有用的。集成进来试试
   *
   * @author maybe
   */
  @Configuration
  @Slf4j
  public class DruidConfig {
  
      //使用@value注入配置文件中信息
      @Value("${spring.datasource.type}")
      private String dbType;
  
      @Value("${spring.datasource.druid.url}")
      private String dbUrl;
  
      @Value("${spring.datasource.druid.username}")
      private String username;
  
      @Value("${spring.datasource.druid.password}")
      private String password;
  
      @Value("${spring.datasource.druid.public-key}")
      private String publicKey;
  
      @Value("${spring.datasource.druid.driver-class-name}")
      private String driverClassName;
  
      @Value("${spring.datasource.druid.initial-size}")
      private int initialSize;
  
      @Value("${spring.datasource.druid.min-idle}")
      private int minIdle;
  
      @Value("${spring.datasource.druid.max-active}")
      private int maxActive;
  
      @Value("${spring.datasource.druid.max-wait}")
      private int maxWait;
  
      @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
      private int timeBetweenEvictionRunsMillis;
  
      @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
      private int minEvictableIdleTimeMillis;
  
      @Value("${spring.datasource.druid.validation-query}")
      private String validationQuery;
  
      @Value("${spring.datasource.druid.test-while-idle}")
      private boolean testWhileIdle;
  
      @Value("${spring.datasource.druid.test-on-borrow}")
      private boolean testOnBorrow;
  
      @Value("${spring.datasource.druid.test-on-return}")
      private boolean testOnReturn;
  
      @Value("${spring.datasource.druid.filters}")
      private String filters;
  
      @Value("${spring.datasource.druid.log-slow-sql}")
      private String logSlowSql;
  
      @Value("${spring.datasource.druid.connection-properties}")
      private String connectionProperties;
  
      @Bean
      public ServletRegistrationBean druidServlet() throws Exception {
          ServletRegistrationBean reg = new ServletRegistrationBean();
          reg.setServlet(new StatViewServlet());
          reg.addUrlMappings("/druid/*");//配置访问URL
          reg.addInitParameter("loginUsername", username);  //配置用户名，这里使用数据库账号。
          reg.addInitParameter("loginPassword", ConfigTools.decrypt(publicKey, password));  //配置用户名，这里使用数据库密码
          reg.addInitParameter("logSlowSql", logSlowSql);   //是否启用慢sql
          return reg;
      }
  
      @Bean
      public FilterRegistrationBean filterRegistrationBean() {
          FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
          filterRegistrationBean.setFilter(new WebStatFilter());
          filterRegistrationBean.addUrlPatterns("/*");
          filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");  //配置那些资源不被拦截
          filterRegistrationBean.addInitParameter("profileEnable", "true");
          return filterRegistrationBean;
      }
  
      /**
       * 这个应该是数据库连接池配置
       *
       * @return
       * @throws Exception
       */
      @Bean
      public DataSource druidDataSource() throws Exception {
          DruidDataSource datasource = new DruidDataSource();
          datasource.setDbType(dbType);
          datasource.setUrl(dbUrl);
          datasource.setUsername(username);
          datasource.setPassword(ConfigTools.decrypt(publicKey, password));
          datasource.setDriverClassName(driverClassName);
          datasource.setInitialSize(initialSize);
          datasource.setMinIdle(minIdle);
          datasource.setMaxActive(maxActive);
          datasource.setMaxWait(maxWait);
          datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
          datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
          datasource.setValidationQuery(validationQuery);
          datasource.setTestWhileIdle(testWhileIdle);
          datasource.setTestOnBorrow(testOnBorrow);
          datasource.setTestOnReturn(testOnReturn);
          datasource.setFilters(filters);
          datasource.setConnectionProperties(connectionProperties);
          try {
              datasource.setFilters(filters);
          } catch (SQLException e) {
              log.error("druid configuration initialization filter:{}", e);
          }
          return datasource;
      }
  }
  ```

- **yml文件**

  ```yml
  spring:
    application:
      name: springboot-mybatis
    datasource:
      name: dataSource1 #如果存在多个数据源，监控的时候可以通过名字来区分开来。如果没有配置，将会生成一个名字，格式是："DataSource-" + System.identityHashCode(this)
      type: com.alibaba.druid.pool.DruidDataSource
      #druid相关配置
      druid:
        #配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        filters: config,stat,log4j
        driver-class-name: com.mysql.jdbc.Driver
        #基本属性
        url: jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=true
        username: root
        password: Rc6i15Md7rMzU8jt8VUOZTfLWshYTgXZs39MFMLi4Z6bJbcYMF3DHzd0pKi+uoOQDn3+IL8sGuQ2KM/LEvgTMQ==
        #配置初始化大小/最小/最大
        initial-size: 5
        min-idle: 5
        max-active: 20
        #获取连接等待超时时间
        max-wait: 60000
        #间隔多久进行一次检测，检测需要关闭的空闲连接
        time-between-eviction-runs-millis: 60000
        #一个连接在池中最小生存的时间
        min-evictable-idle-time-millis: 30000
        #验证数据库是否链接
        validation-query: SELECT 'x'
        #当链接空闲时，是否测试链接可用性
        test-while-idle: true
        #当从连接池拿到连接时，是否测试连接的可用性
        test-on-borrow: false
        #当链接归还连接池时，是否测试链接可用性
        test-on-return: false
        #打开PSCache，并指定每个连接上PSCache的大小。oracle设为true，mysql设为false。分库分表较多推荐设置为false
        pool-prepared-statements: false
        max-pool-prepared-statement-per-connection-size: 20
        log-slow-sql: true
        public-key: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOclQrPeCT+uuuOI/eEEt6izDwBIPk+bTeR471om1RQZCU6mr+P/gWiycgb+dsm9XpiJQjNZvuBOaF8fljwHe1cCAwEAAQ==
        connection-properties: druid.stat.mergeSql=true,druid.stat.slowSqlMillis=5000;config.decrpt=true
  ```

  `mybatis`模块