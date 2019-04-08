package com.deershop.dao.r_goods_promotion;
import java.util.List;
import java.util.Map;
import com.deershop.model.r_goods_promotion.R_goods_promotion;
	public interface IR_goods_promotionMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public R_goods_promotion selectr_goods_promotionById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<R_goods_promotion> selectr_goods_promotionByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountr_goods_promotionByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updater_goods_promotion(R_goods_promotion r_goods_promotion);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addr_goods_promotion(R_goods_promotion r_goods_promotion);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdr_goods_promotion(List<R_goods_promotion> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleter_goods_promotion(String id);

}

