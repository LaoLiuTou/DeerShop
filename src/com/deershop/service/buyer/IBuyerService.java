package com.deershop.service.buyer;
import java.util.List;
import java.util.Map;
import com.deershop.model.buyer.Buyer;
public interface IBuyerService {
	/**
	* 通过id选取
	* @return
	*/
	public Buyer selectBuyerById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Buyer> selectBuyerByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountBuyerByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateBuyer(Buyer buyer);

	/**
	* 添加 
	* @return
	*/ 
	public int addBuyer(Buyer buyer);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdBuyer(List<Buyer> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteBuyer(String id);

}

