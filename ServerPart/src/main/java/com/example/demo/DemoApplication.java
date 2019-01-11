package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class DemoApplication {




    public static void main(String[] args) {

        //buradaki path'i kendi pcnizdeki ile degistirin
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("C:\\Users\\Johnson\\Desktop\\demo\\shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);


        SpringApplication.run(DemoApplication.class,args);
    }

}

