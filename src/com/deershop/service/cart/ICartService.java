package com.deershop.service.cart;
import java.util.List;
import java.util.Map;
import com.deershop.model.cart.Cart;
public interface ICartService {
	/**
	* 通过id选取
	* @return
	*/
	public Cart selectCartById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Cart> selectCartByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCartByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateCart(Cart cart);

	/**
	* 添加 
	* @return
	*/ 
	public int addCart(Cart cart);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdCart(List<Cart> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteCart(String id);

}

