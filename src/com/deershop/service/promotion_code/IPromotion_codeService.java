package com.deershop.service.promotion_code;
import java.util.List;
import java.util.Map;
import com.deershop.model.promotion_code.Promotion_code;
public interface IPromotion_codeService {
	/**
	* 通过id选取
	* @return
	*/
	public Promotion_code selectPromotion_codeById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Promotion_code> selectPromotion_codeByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountPromotion_codeByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updatePromotion_code(Promotion_code promotion_code);

	/**
	* 添加 
	* @return
	*/ 
	public int addPromotion_code(Promotion_code promotion_code);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdPromotion_code(List<Promotion_code> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deletePromotion_code(String id);

}

