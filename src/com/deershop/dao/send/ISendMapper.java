package com.deershop.dao.send;
import java.util.List;
import java.util.Map;
import com.deershop.model.send.Send;
	public interface ISendMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Send selectsendById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Send> selectsendByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountsendByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatesend(Send send);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addsend(Send send);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdsend(List<Send> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletesend(String id);

}

