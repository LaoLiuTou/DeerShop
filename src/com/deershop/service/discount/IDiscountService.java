package com.deershop.service.discount;
import java.util.List;
import java.util.Map;
import com.deershop.model.discount.Discount;
public interface IDiscountService {
	/**
	* 通过id选取
	* @return
	*/
	public Discount selectDiscountById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Discount> selectDiscountByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountDiscountByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateDiscount(Discount discount);

	/**
	* 添加 
	* @return
	*/ 
	public int addDiscount(Discount discount);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdDiscount(List<Discount> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteDiscount(String id);

}

