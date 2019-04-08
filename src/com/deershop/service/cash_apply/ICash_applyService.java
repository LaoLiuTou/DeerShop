package com.deershop.service.cash_apply;
import java.util.List;
import java.util.Map;
import com.deershop.model.cash_apply.Cash_apply;
public interface ICash_applyService {
	/**
	* 通过id选取
	* @return
	*/
	public Cash_apply selectCash_applyById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Cash_apply> selectCash_applyByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCash_applyByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateCash_apply(Cash_apply cash_apply);
	public int offlinePay(Cash_apply cash_apply);
	public String payToCard(Cash_apply cash_apply);
	public String payToWechat(Cash_apply cash_apply);

	/**
	* 添加 
	* @return
	*/ 
	public int addCash_apply(Cash_apply cash_apply);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdCash_apply(List<Cash_apply> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteCash_apply(String id);

}

