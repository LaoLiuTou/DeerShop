package com.deershop.service.r_goods_promotion;
import java.util.List;
import java.util.Map;
import com.deershop.model.r_goods_promotion.R_goods_promotion;
public interface IR_goods_promotionService {
	/**
	* 通过id选取
	* @return
	*/
	public R_goods_promotion selectR_goods_promotionById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_goods_promotion> selectR_goods_promotionByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_goods_promotionByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateR_goods_promotion(R_goods_promotion r_goods_promotion);

	/**
	* 添加 
	* @return
	*/ 
	public int addR_goods_promotion(R_goods_promotion r_goods_promotion);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdR_goods_promotion(List<R_goods_promotion> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteR_goods_promotion(String id);

}

