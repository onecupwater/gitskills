package com.abc.memberserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
//"order-server"对应订单系统在注册中心起的名字
@FeignClient(value = "order-server",fallback = MemberFeignService.class)
public interface OrderFeign {
    @RequestMapping("orderTest") //对应订单系统中具体要访问的路径
    public String orderTest();
}
