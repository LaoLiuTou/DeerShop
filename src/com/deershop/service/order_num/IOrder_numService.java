package com.deershop.service.order_num;
import java.util.List;
import java.util.Map;
import com.deershop.model.order_num.Order_num;
public interface IOrder_numService {
	/**
	* 通过id选取
	* @return
	*/
	public Order_num selectOrder_numById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Order_num> selectOrder_numByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrder_numByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateOrder_num(Order_num order_num);

	/**
	* 添加 
	* @return
	*/ 
	public int addOrder_num(Order_num order_num);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdOrder_num(List<Order_num> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteOrder_num(String id);

}

