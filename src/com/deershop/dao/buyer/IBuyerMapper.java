package com.deershop.dao.buyer;
import java.util.List;
import java.util.Map;
import com.deershop.model.buyer.Buyer;
	public interface IBuyerMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Buyer selectbuyerById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Buyer> selectbuyerByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountbuyerByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatebuyer(Buyer buyer);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addbuyer(Buyer buyer);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdbuyer(List<Buyer> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletebuyer(String id);

}

