package com.abc.memberserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @RequestMapping("memberTest")
    @ResponseBody
    public String memberTest(){
        return "this is member";
    }
}
