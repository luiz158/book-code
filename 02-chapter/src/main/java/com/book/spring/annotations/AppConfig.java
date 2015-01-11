package com.book.spring.annotations;

import com.book.basics.JdbcPersonRepository;
import com.book.basics.PersonRepository;
import com.book.basics.PersonService;
import com.book.basics.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 1/11/15.
 */
@Configuration
@PropertySource(name="dbProp", value = "classpath:datasource/db.properties")
public class AppConfig {

    @Autowired
    Environment env;
    
    @Bean(name="personService")
    public PersonService getPersonService(){
        return new PersonServiceImpl(getPersonRepository());
    }
    
    @Bean(name="personRepository")
    public PersonRepository getPersonRepository(){
        return new JdbcPersonRepository();
    }
    
    @Bean(name="dataSource")
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("username"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;
    }
}