package com.deershop.dao.seller_card;
import java.util.List;
import java.util.Map;
import com.deershop.model.seller_card.Seller_card;
	public interface ISeller_cardMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Seller_card selectseller_cardById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Seller_card> selectseller_cardByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountseller_cardByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateseller_card(Seller_card seller_card);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addseller_card(Seller_card seller_card);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdseller_card(List<Seller_card> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteseller_card(String id);

}

