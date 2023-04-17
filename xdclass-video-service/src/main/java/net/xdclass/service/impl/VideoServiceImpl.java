package net.xdclass.service.impl;

import net.xdclass.Video;
import net.xdclass.dao.VideoMapper;
import net.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;
//    spring 事务底层使用了 aop
//  1。类交给spring处理
//  2。事务注解不支持事务
//  3。数据库开启事务
//  4。异常捕获
//  5。调用内部方法
//  6。非public 修饰 --  Transactional 底层有非public 判断。

    @Override
    @Transactional
    public Video findById(int videoId) {
        return videoMapper.findById(videoId);
    }
}
