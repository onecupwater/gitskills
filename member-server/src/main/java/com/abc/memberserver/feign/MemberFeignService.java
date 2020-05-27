package com.abc.memberserver.feign;

import org.springframework.stereotype.Component;

@Component
public class MemberFeignService implements OrderFeign{
    @Override
    public String orderTest() {
        return "系统繁忙，请重新刷新";
    }
}
