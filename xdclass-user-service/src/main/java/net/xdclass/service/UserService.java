package net.xdclass.service;

import lombok.extern.slf4j.Slf4j;
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

    public void save(UserModel userModel){
        userMapper.insertIgnoreEmpty(userModel);
    }

    public UserModel findById(Integer id){
        log.info(id.toString());
        UserModel byId = userMapper.getById(id);
        return byId;
    }
}