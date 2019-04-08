package com.deershop.service.r_goods_discount;
import java.util.List;
import java.util.Map;
import com.deershop.model.r_goods_discount.R_goods_discount;
public interface IR_goods_discountService {
	/**
	* 通过id选取
	* @return
	*/
	public R_goods_discount selectR_goods_discountById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_goods_discount> selectR_goods_discountByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_goods_discountByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateR_goods_discount(R_goods_discount r_goods_discount);

	/**
	* 添加 
	* @return
	*/ 
	public int addR_goods_discount(R_goods_discount r_goods_discount);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdR_goods_discount(List<R_goods_discount> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteR_goods_discount(String id);

}

