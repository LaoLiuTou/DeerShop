package com.deershop.service.goods_message;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.goods_message.IGoods_messageMapper;
import com.deershop.model.goods_message.Goods_message;
public class Goods_messageServiceImpl  implements IGoods_messageService {

	@Autowired
	private IGoods_messageMapper iGoods_messageMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Goods_message selectGoods_messageById(String id){
		return iGoods_messageMapper.selectgoods_messageById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Goods_message> selectGoods_messageByParam(Map paramMap){ 
		return iGoods_messageMapper.selectgoods_messageByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountGoods_messageByParam(Map paramMap){ 
		return iGoods_messageMapper.selectCountgoods_messageByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateGoods_message(Goods_message goods_message){
		return iGoods_messageMapper.updategoods_message(goods_message);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addGoods_message(Goods_message goods_message){
		return iGoods_messageMapper.addgoods_message(goods_message);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdGoods_message(List<Goods_message> list){
		return iGoods_messageMapper.muladdgoods_message(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteGoods_message(String id){
		return iGoods_messageMapper.deletegoods_message(id);
	}

}

