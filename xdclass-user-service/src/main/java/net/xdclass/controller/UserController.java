package net.xdclass.controller;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;
import net.xdclass.entity.OrderInfoModel;
import net.xdclass.OrderInfoApi;
import org.springframework.web.bind.annotation.*;

import net.xdclass.entity.UserModel;
import net.xdclass.service.UserService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author cui
 * @date 2023-11-28
 */
@RestController("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private OrderInfoApi orderInfoServiceApi;


    @ResponseBody
    @PostMapping({"/save"})
    @ApiOperation(value = "新增用户信息", httpMethod = "POST", response = Map.class)
    public void save(@RequestBody UserModel userModel) {
        userService.save(userModel);
    }

    @ResponseBody
    @PostMapping({"/findById"})
    @ApiOperation(value = "查询用户信息", httpMethod = "POST", response = Map.class)
    public UserModel findById(@RequestParam("id") Integer id) {
        return userService.findById(id);
    }

    @ResponseBody
    @PostMapping({"/findOrderInfoById"})
    @ApiOperation(value = "查询用户信息", httpMethod = "POST", response = Map.class)
    public OrderInfoModel findOrderInfoById(@RequestParam("id") Integer id) {
        return orderInfoServiceApi.findById(id);
    }


    @ResponseBody
    @PostMapping({"/saveUserAndUser"})
    @ApiOperation(value = "新增用户信息以及用户订单", httpMethod = "POST", response = Map.class)
    public void saveUserAndUser(@RequestBody UserModel userModel) {
        userModel.setBirthday(new Date());
        OrderInfoModel orderInfoModel = new OrderInfoModel();
        orderInfoModel.setOrderNo(userModel.getId());
        orderInfoModel.setId(userModel.getId());
        orderInfoModel.setOrderName(userModel.getName());
        orderInfoModel.setOrderPrice(new BigDecimal(1.00));
        orderInfoModel.setOrderAddress("地址");
        userService.saveUserAndUser(userModel, orderInfoModel);
    }

}