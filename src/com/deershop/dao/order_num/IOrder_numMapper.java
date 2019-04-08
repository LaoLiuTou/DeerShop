package com.deershop.dao.order_num;
import java.util.List;
import java.util.Map;
import com.deershop.model.order_num.Order_num;
	public interface IOrder_numMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Order_num selectorder_numById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Order_num> selectorder_numByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountorder_numByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateorder_num(Order_num order_num);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addorder_num(Order_num order_num);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdorder_num(List<Order_num> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteorder_num(String id);

}

