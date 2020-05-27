package com.abc.orderserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("orderTest")
    @ResponseBody
    public String orderTest(){
        return "This is a order";
    }
}
