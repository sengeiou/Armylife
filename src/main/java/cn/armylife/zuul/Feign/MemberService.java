package cn.armylife.zuul.Feign;

import cn.armylife.zuul.Hystrix.MemberServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "memberservice",fallback = MemberServiceHystrix.class)
public interface MemberService {

    @RequestMapping(value = "reloadSession")
    int reloadSession(@RequestParam Integer memberId);
}
