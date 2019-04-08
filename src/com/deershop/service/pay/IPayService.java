package com.deershop.service.pay;
import java.util.List;
import java.util.Map;
import com.deershop.model.pay.Pay;
public interface IPayService {
	/**
	* 通过id选取
	* @return
	*/
	public Pay selectPayById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Pay> selectPayByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountPayByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updatePay(Pay pay);
	public int updatePayByPayId(Pay pay);

	/**
	* 添加 
	* @return
	*/ 
	public int addPay(Pay pay);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdPay(List<Pay> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deletePay(String id);

}

