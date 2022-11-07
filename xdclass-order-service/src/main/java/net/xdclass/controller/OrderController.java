package net.xdclass.controller;


import net.xdclass.Video;
import net.xdclass.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/v1/video_order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/save")
    public Object save(int videoId){
//        Video forObject = restTemplate.getForObject("http://localhost:9000/api/v1/video/findById?videoId=" + videoId, Video.class);
        List<ServiceInstance> instances = discoveryClient.getInstances("xdclass-video-service");
        ServiceInstance serviceInstance = instances.get(0);
//        Video forObject = restTemplate.getForObject("http://xdclass-video-service/api/v1/video/findById?videoId=" + videoId, Video.class);
//        return forObject;
        Video byId = orderService.findById(videoId);

        return byId;
    }

    @PostMapping("save1")
    public Object save(@RequestBody Video video){
        System.out.println(video.getTitle());
        Object save = orderService.save(video);

        return "1234";
    }

    @RequestMapping("list")
    public HashMap list(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("title","name1");
        hashMap.put("title1","name2");
        return hashMap;
    }
}
