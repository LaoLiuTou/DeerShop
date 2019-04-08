package com.deershop.service.full_discount;
import java.util.List;
import java.util.Map;
import com.deershop.model.full_discount.Full_discount;
public interface IFull_discountService {
	/**
	* 通过id选取
	* @return
	*/
	public Full_discount selectFull_discountById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Full_discount> selectFull_discountByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountFull_discountByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateFull_discount(Full_discount full_discount);

	/**
	* 添加 
	* @return
	*/ 
	public int addFull_discount(Full_discount full_discount);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdFull_discount(List<Full_discount> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteFull_discount(String id);

}

