package com.deershop.dao.promotion_code;
import java.util.List;
import java.util.Map;
import com.deershop.model.promotion_code.Promotion_code;
	public interface IPromotion_codeMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Promotion_code selectpromotion_codeById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Promotion_code> selectpromotion_codeByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountpromotion_codeByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatepromotion_code(Promotion_code promotion_code);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addpromotion_code(Promotion_code promotion_code);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdpromotion_code(List<Promotion_code> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletepromotion_code(String id);

}

