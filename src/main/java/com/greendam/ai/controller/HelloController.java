package com.greendam.ai.controller;


import com.greendam.ai.common.Result;
import com.greendam.ai.common.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>初始化控制器</h3>
 * <p>用于测试产品是否在线，与业务无关</p>
 *
 * @author ForeverGreenDam
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
    /**
     * <h3>国际化测试接口</h3>
     * <p>用于测试国际化消息功能，接收文本参数并返回国际化处理后的消息</p>
     *
     * @param text 需要进行国际化处理的文本参数，通过路径变量传递
     * @return 包含国际化处理后消息的统一响应结果
     */
    @GetMapping("/i18n/{text}")
    public Result<String> i18n(@PathVariable("text") String text){
        return Result.ok(MessageUtil.getMessage("test.i18n", new String[]{text}));
    }
}
