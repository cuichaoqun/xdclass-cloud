package net.xdclass.service;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.entity.OrderInfoModel;
import net.xdclass.mapper.OrderInfoMapper;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cui
 * @date 2023-11-29
 */
@Service
@Slf4j
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public void save(OrderInfoModel orderInfoModel){
        orderInfoMapper.insertIgnoreEmpty(orderInfoModel);
    }

    public OrderInfoModel findById(Integer id){
        log.info(id.toString());
        OrderInfoModel byId = orderInfoMapper.getById(id);
        return byId;
    }
}