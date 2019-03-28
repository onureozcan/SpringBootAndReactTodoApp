package org.zero.todoapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zero.todoapp.filters.CORSFilter;
import org.zero.todoapp.filters.CustomAuthFilter;
import org.zero.todoapp.services.UserService;

import javax.servlet.Filter;

@SpringBootApplication
@EnableAutoConfiguration
public class TodoAppMain {

    private static Logger logger = LoggerFactory.getLogger(TodoAppMain.class);

    public static void main(String[] args) {
        SpringApplication.run(TodoAppMain.class, args);
        logger.info("App started");
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    public InitializingBean getInitializingBean(){
        return ()->{

        };
    }

    @Bean
    public Filter getCorsFilter() {
        return new CORSFilter();
    }

    @Bean
    public Filter getAuthFilter() {
        return new CustomAuthFilter();
    }
}
