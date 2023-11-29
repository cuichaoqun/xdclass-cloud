package net.xdclass.controller;

import io.swagger.annotations.ApiOperation;
import net.xdclass.constant.ControllerConstant;
import net.xdclass.entity.OrderInfoModel;
import net.xdclass.service.OrderInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author cui
 * @date 2023-11-29
 */
@RestController
@RequestMapping(ControllerConstant.ORDER_CONTROLLER + "/orderInfo")
public class OrderInfoController{

    @Resource
    private OrderInfoService orderInfoService;

    @ResponseBody
    @PostMapping({"/save"})
    @ApiOperation(value="新增订单信息",httpMethod="POST",response= Map.class)
    public void save(@RequestBody OrderInfoModel orderInfoModel) {
        orderInfoService.save(orderInfoModel);
    }

    @ResponseBody
    @PostMapping({"/findById"})
    @ApiOperation(value="查询订单信息",httpMethod="POST",response= Map.class)
    public OrderInfoModel findById(@RequestParam("id") Integer id) {
        return orderInfoService.findById(id);
    }
}