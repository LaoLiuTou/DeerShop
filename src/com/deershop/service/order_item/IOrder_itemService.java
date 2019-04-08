package com.deershop.service.order_item;
import java.util.List;
import java.util.Map;
import com.deershop.model.order_item.Order_item;
public interface IOrder_itemService {
	/**
	* 通过id选取
	* @return
	*/
	public Order_item selectOrder_itemById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Order_item> selectOrder_itemByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrder_itemByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateOrder_item(Order_item order_item);

	/**
	* 添加 
	* @return
	*/ 
	public int addOrder_item(Order_item order_item);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdOrder_item(List<Order_item> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteOrder_item(String id);

}

