package com.deershop.service.seller_card;
import java.util.List;
import java.util.Map;
import com.deershop.model.seller_card.Seller_card;
public interface ISeller_cardService {
	/**
	* 通过id选取
	* @return
	*/
	public Seller_card selectSeller_cardById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Seller_card> selectSeller_cardByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSeller_cardByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateSeller_card(Seller_card seller_card);

	/**
	* 添加 
	* @return
	*/ 
	public int addSeller_card(Seller_card seller_card);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdSeller_card(List<Seller_card> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteSeller_card(String id);

}

