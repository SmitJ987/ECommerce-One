package com.smitjdev.demospringbootone;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
public class HelloService {

//    @RequestMapping
//    public String sayHello("/hello")
//    {
//        return "Hello World";
//    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello()
    {
        return "hello world";
    }

    @RequestMapping(value = "/user/{id}", method=RequestMethod.GET)
    public String updateUser(@PathVariable("id") String idNew)
    {
        return "updating user with id: "+idNew;
    }


}
