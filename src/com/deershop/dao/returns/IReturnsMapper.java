package com.deershop.dao.returns;
import java.util.List;
import java.util.Map;
import com.deershop.model.returns.Returns;
	public interface IReturnsMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Returns selectreturnsById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Returns> selectreturnsByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountreturnsByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatereturns(Returns returns);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addreturns(Returns returns);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdreturns(List<Returns> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletereturns(String id);

}

