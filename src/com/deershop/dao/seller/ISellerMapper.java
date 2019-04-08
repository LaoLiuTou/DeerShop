package com.deershop.dao.seller;
import java.util.List;
import java.util.Map;
import com.deershop.model.seller.Seller;
	public interface ISellerMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Seller selectsellerById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Seller> selectsellerByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountsellerByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateseller(Seller seller);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addseller(Seller seller);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdseller(List<Seller> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteseller(String id);

}

