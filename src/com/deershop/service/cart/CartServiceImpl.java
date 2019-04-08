package com.deershop.service.cart;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.cart.ICartMapper;
import com.deershop.model.cart.Cart;
public class CartServiceImpl  implements ICartService {

	@Autowired
	private ICartMapper iCartMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Cart selectCartById(String id){
		return iCartMapper.selectcartById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Cart> selectCartByParam(Map paramMap){ 
		return iCartMapper.selectcartByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCartByParam(Map paramMap){ 
		return iCartMapper.selectCountcartByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateCart(Cart cart){
		return iCartMapper.updatecart(cart);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addCart(Cart cart){
		return iCartMapper.addcart(cart);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdCart(List<Cart> list){
		return iCartMapper.muladdcart(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteCart(String id){
		return iCartMapper.deletecart(id);
	}

}

