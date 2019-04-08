package com.deershop.dao.discount;
import java.util.List;
import java.util.Map;
import com.deershop.model.discount.Discount;
	public interface IDiscountMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Discount selectdiscountById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Discount> selectdiscountByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountdiscountByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatediscount(Discount discount);
	/**
 	* 添加 
 	* @return
 */ 
	public  int adddiscount(Discount discount);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladddiscount(List<Discount> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletediscount(String id);

}

