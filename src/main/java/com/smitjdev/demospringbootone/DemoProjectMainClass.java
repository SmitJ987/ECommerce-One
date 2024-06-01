package com.smitjdev.demospringbootone;

import com.smitjdev.demospringbootone.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoProjectMainClass {

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectMainClass.class, args);

//
//        Product pr = new Product();
//
//        pr.getTitle();
//        pr.setTitle("ab");
    }
}