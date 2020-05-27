package com.abc.memberserver.controller;

import com.abc.memberserver.feign.OrderFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("member")
    @ResponseBody
    public String member(){
       return orderFeign.orderTest();
    }

}
