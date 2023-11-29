package net.xdclass.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserModel implements Serializable {

	/** 主键 */
	private Integer id;
    
	/** 姓名 */
	private String name;
    
	/** 生日 */
	private Date birthday;
    
	/** 地址 */
	private String address;
    
	
}