package net.xdclass;

import net.xdclass.constant.AppNameConstant;
import net.xdclass.constant.ControllerConstant;
import net.xdclass.entity.OrderInfoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = AppNameConstant.APPNAME_ORDER)
@RequestMapping(ControllerConstant.ORDER_CONTROLLER + "/orderInfo")
public interface OrderInfoApi {

    @PostMapping({"/save"})
    void save(@RequestBody OrderInfoModel orderInfoModel);

    @PostMapping({"/findById"})
    OrderInfoModel findById(@RequestParam("id") Integer id);

}

