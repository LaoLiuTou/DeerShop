package com.deershop.dao.goods_message;
import java.util.List;
import java.util.Map;
import com.deershop.model.goods_message.Goods_message;
	public interface IGoods_messageMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Goods_message selectgoods_messageById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Goods_message> selectgoods_messageByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountgoods_messageByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updategoods_message(Goods_message goods_message);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addgoods_message(Goods_message goods_message);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdgoods_message(List<Goods_message> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletegoods_message(String id);

}

