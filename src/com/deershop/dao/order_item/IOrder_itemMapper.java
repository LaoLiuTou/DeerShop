package com.deershop.dao.order_item;
import java.util.List;
import java.util.Map;
import com.deershop.model.order_item.Order_item;
	public interface IOrder_itemMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Order_item selectorder_itemById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Order_item> selectorder_itemByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountorder_itemByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateorder_item(Order_item order_item);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addorder_item(Order_item order_item);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdorder_item(List<Order_item> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteorder_item(String id);

}

