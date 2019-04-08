package com.deershop.dao.pay;
import java.util.List;
import java.util.Map;
import com.deershop.model.pay.Pay;
	public interface IPayMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Pay selectpayById(String id);
	public List<Pay> selectpayByPayId(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Pay> selectpayByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountpayByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatepay(Pay pay);
	public  int updatePayByPayId(Pay pay);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addpay(Pay pay);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdpay(List<Pay> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletepay(String id);

}

