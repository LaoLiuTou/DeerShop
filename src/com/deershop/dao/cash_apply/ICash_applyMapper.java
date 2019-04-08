package com.deershop.dao.cash_apply;
import java.util.List;
import java.util.Map;
import com.deershop.model.cash_apply.Cash_apply;
	public interface ICash_applyMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Cash_apply selectcash_applyById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Cash_apply> selectcash_applyByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountcash_applyByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatecash_apply(Cash_apply cash_apply);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addcash_apply(Cash_apply cash_apply);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdcash_apply(List<Cash_apply> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletecash_apply(String id);

}

