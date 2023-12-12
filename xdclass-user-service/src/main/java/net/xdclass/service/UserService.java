package net.xdclass.service;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.OrderInfoApi;
import net.xdclass.entity.OrderInfoModel;
import net.xdclass.entity.UserModel;
import net.xdclass.mapper.UserMapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cui
 * @date 2023-11-28
 */
@Service
@Slf4j
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderInfoApi orderInfoServiceApi;

    public void save(UserModel userModel){
        userMapper.insertIgnoreEmpty(userModel);
    }

    public UserModel findById(Integer id){
        log.info(id.toString());
        UserModel byId = userMapper.getById(id);
        return byId;
    }


//    @GlobalTransactional
    public void saveUserAndUser(UserModel userModel, OrderInfoModel orderInfoModel){
        userMapper.insertIgnoreEmpty(userModel);
        orderInfoServiceApi.save(orderInfoModel);
        int i= 1/0;
    }
}