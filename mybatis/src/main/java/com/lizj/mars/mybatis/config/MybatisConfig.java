package com.lizj.mars.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = MybatisConfig.PACKAGE, sqlSessionFactoryRef = "bizSqlSessionFactory")
public class MybatisConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    static final String PACKAGE = "com.lizj.mars.mybatis.mapper";
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/*.xml";

    @Bean(name = "bizDataSource")
    @Primary
    public DataSource bizDataSource() {
        DruidDataSource bizDataSource = new DruidDataSource();
        bizDataSource.setUrl(dbUrl);
        bizDataSource.setUsername(username);
        bizDataSource.setPassword(password);
        bizDataSource.setDriverClassName(driverClassName);
        bizDataSource.setInitialSize(initialSize);
        bizDataSource.setMinIdle(minIdle);
        bizDataSource.setMaxActive(maxActive);
        bizDataSource.setMaxWait(maxWait);
        bizDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        bizDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        bizDataSource.setTestWhileIdle(testWhileIdle);
        bizDataSource.setTestOnBorrow(testOnBorrow);
        bizDataSource.setTestOnReturn(testOnReturn);
        bizDataSource.setPoolPreparedStatements(poolPreparedStatements);
        bizDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        return bizDataSource;
    }

    @Bean(name = "bizTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("bizDataSource") DataSource bizDataSource) {
        return new DataSourceTransactionManager(bizDataSource);
    }

    @Bean(name = "bizSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("bizDataSource") DataSource bizDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(bizDataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(MybatisConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
