package com.deershop.dao.cart;
import java.util.List;
import java.util.Map;
import com.deershop.model.cart.Cart;
	public interface ICartMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Cart selectcartById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Cart> selectcartByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountcartByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatecart(Cart cart);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addcart(Cart cart);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdcart(List<Cart> list);
	/**
 	* 删除 
 	* @return 
	 */ 
	public  int deletecart(String id);
 
	public  int deletecartByParam(Cart cart);

}

