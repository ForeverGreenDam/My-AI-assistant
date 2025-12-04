package com.greendam.ai.controller;


import com.greendam.ai.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    /**
    * <h3>初始化控制器</h3>
     * <p>用于测试产品是否在线，与业务无关</p>
    */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {
    /**
     * <h3>健康检查接口</h3>
     *
     * @return 返回成功结果，用于确认服务是否正常运行
     */
    @GetMapping("/ping")
    public Result<Void> ping(){
        return Result.ok();
    }
}
