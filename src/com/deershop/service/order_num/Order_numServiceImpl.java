package com.deershop.service.order_num;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.order_num.IOrder_numMapper;
import com.deershop.model.order_num.Order_num;
public class Order_numServiceImpl  implements IOrder_numService {

	@Autowired
	private IOrder_numMapper iOrder_numMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Order_num selectOrder_numById(String id){
		return iOrder_numMapper.selectorder_numById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Order_num> selectOrder_numByParam(Map paramMap){ 
		return iOrder_numMapper.selectorder_numByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrder_numByParam(Map paramMap){ 
		return iOrder_numMapper.selectCountorder_numByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateOrder_num(Order_num order_num){
		return iOrder_numMapper.updateorder_num(order_num);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addOrder_num(Order_num order_num){
		return iOrder_numMapper.addorder_num(order_num);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdOrder_num(List<Order_num> list){
		return iOrder_numMapper.muladdorder_num(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteOrder_num(String id){
		return iOrder_numMapper.deleteorder_num(id);
	}

}

