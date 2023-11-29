package net.xdclass.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderInfoModel implements Serializable {

	private Integer id;
    
	private String orderName;
    
	private Integer orderNo;
    
	private String orderAddress;
    
	private BigDecimal orderPrice;
    
	private Integer orderNum;
    
	private String orderMessage;
    
	
}