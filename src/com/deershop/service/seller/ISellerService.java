package com.deershop.service.seller;
import java.util.List;
import java.util.Map;
import com.deershop.model.seller.Seller;
public interface ISellerService {
	/**
	* 通过id选取
	* @return
	*/
	public Seller selectSellerById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Seller> selectSellerByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSellerByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateSeller(Seller seller);

	/**
	* 添加 
	* @return
	*/ 
	public int addSeller(Seller seller);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdSeller(List<Seller> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteSeller(String id);

}

