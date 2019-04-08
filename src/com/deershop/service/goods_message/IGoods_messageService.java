package com.deershop.service.goods_message;
import java.util.List;
import java.util.Map;
import com.deershop.model.goods_message.Goods_message;
public interface IGoods_messageService {
	/**
	* 通过id选取
	* @return
	*/
	public Goods_message selectGoods_messageById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Goods_message> selectGoods_messageByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountGoods_messageByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateGoods_message(Goods_message goods_message);

	/**
	* 添加 
	* @return
	*/ 
	public int addGoods_message(Goods_message goods_message);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdGoods_message(List<Goods_message> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteGoods_message(String id);

}

