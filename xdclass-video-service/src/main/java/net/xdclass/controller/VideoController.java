package net.xdclass.controller;

import net.xdclass.Video;
import net.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/video")
public class VideoController {


//    http://127.0.0.1:9000/api/v1/video/findById?videoId=40
    @Autowired
    private VideoService videoService;

    @RequestMapping("findById")
    public Object findById(int videoId, HttpServletRequest httpServletRequest){
        Video byId = videoService.findById(videoId);
        String serverName = httpServletRequest.getServerName();
        serverName = serverName + ":" +httpServletRequest.getServerPort();
        byId.setServeInfo(serverName);
        return byId;
    }

    @PostMapping("save")
    public int save(@RequestBody Video video){
        System.out.println(video.getTitle());
        return 123;
    }



}
