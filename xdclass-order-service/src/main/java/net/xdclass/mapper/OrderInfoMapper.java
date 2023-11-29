package net.xdclass.mapper;

import org.apache.ibatis.annotations.Mapper;
import net.xdclass.entity.OrderInfoModel;
import org.apache.ibatis.annotations.Param;

/**
 *  mapper接口
 * 
 * @author cui
 * @date 2023-11-29
 */
@Mapper
public interface OrderInfoMapper {

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	OrderInfoModel getById(@Param("id") Integer id);
    
    /**
     * 新增，不忽略空字段
     *
     * @param model 新增的记录
     * @return 返回影响行数
     */
	int insertNotIgnoreEmpty(OrderInfoModel model);

	/**
     * 新增，忽略空字段
     *
     * @param model 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreEmpty(OrderInfoModel model);
    
    /**
     * 修改，不忽略空字段
     *
     * @param model 修改的记录
     * @return 返回影响行数
     */
	int updateNotIgnoreEmpty(OrderInfoModel model);

	/**
     * 修改，忽略空字段
     *
     * @param model 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreEmpty(OrderInfoModel model);
	
}