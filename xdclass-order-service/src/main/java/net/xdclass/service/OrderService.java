package net.xdclass.service;

import net.xdclass.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//服务名称记得和nacos保持⼀样
@FeignClient(name="xdclass-video-service")
public interface OrderService {


    @GetMapping(value ="/api/v1/video/findById")
    Video findById(@RequestParam("videoId") int videoId);



    @PostMapping(value ="/api/v1/video/save")
    int save(@RequestBody Video video);
}
