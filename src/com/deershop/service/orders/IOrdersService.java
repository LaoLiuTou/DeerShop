package com.deershop.service.orders;
import java.util.List;
import java.util.Map;

import com.deershop.model.order_item.Order_item;
import com.deershop.model.orders.Orders;
public interface IOrdersService {
	/**
	* 通过id选取
	* @return
	*/
	public Orders selectOrdersById(String id);
	public Orders selectordersByIdApp(String id);
	public Orders selectCdOrdersById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Orders> selectOrdersByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrdersByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateOrders(Orders orders);
	public int updateByOrdernum(Orders orders);

	/**
	* 添加 
	* @return
	*/ 
	public int addOrders(Orders orders,String order_items);
	public int addOrders(Orders orders,List<Order_item> order_items);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdOrders(List<Orders> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteOrders(String id);

}

