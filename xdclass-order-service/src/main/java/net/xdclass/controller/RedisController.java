package net.xdclass.controller;

import net.xdclass.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/redis"))
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("getValue")
    public String getValue(String key){
        Object o = redisUtil.get(key);
        return o.toString();
    }
    @RequestMapping("setCash")
    public String setCash(String key,String value){
        /**
         * 1. OSI 模型的网络层中产生拥塞的主要原因：【口诀：带宽容量处理故障。】
         * （1）缓冲区容量有限。
         * （2）传输线路的带宽有限。
         * （3）网络结点的处理能力有限。
         * （4）网络中某些部分发生了故障。
         * */
        redisUtil.set(key,value);
        return "存储成功";
    }
}
