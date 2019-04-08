package com.deershop.service.r_buyer_promotion;
import java.util.List;
import java.util.Map;
import com.deershop.model.r_buyer_promotion.R_buyer_promotion;
public interface IR_buyer_promotionService {
	/**
	* 通过id选取
	* @return
	*/
	public R_buyer_promotion selectR_buyer_promotionById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_buyer_promotion> selectR_buyer_promotionByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_buyer_promotionByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateR_buyer_promotion(R_buyer_promotion r_buyer_promotion);

	/**
	* 添加 
	* @return
	*/ 
	public int addR_buyer_promotion(R_buyer_promotion r_buyer_promotion);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdR_buyer_promotion(List<R_buyer_promotion> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteR_buyer_promotion(String id);

}

