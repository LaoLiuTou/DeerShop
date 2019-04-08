package com.deershop.service.order_item;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.deershop.dao.goods.IGoodsMapper;
import com.deershop.dao.order_item.IOrder_itemMapper;
import com.deershop.model.goods.Goods;
import com.deershop.model.order_item.Order_item;
public class Order_itemServiceImpl  implements IOrder_itemService {

	@Autowired
	private IOrder_itemMapper iOrder_itemMapper;
	@Autowired
	private IGoodsMapper iGoodsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Order_item selectOrder_itemById(String id){
		return iOrder_itemMapper.selectorder_itemById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Order_item> selectOrder_itemByParam(Map paramMap){ 
		return iOrder_itemMapper.selectorder_itemByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrder_itemByParam(Map paramMap){ 
		return iOrder_itemMapper.selectCountorder_itemByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateOrder_item(Order_item order_item){
		return iOrder_itemMapper.updateorder_item(order_item);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addOrder_item(Order_item order_item){
		return iOrder_itemMapper.addorder_item(order_item);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdOrder_item(List<Order_item> list){
		int result=0;
		result=iOrder_itemMapper.muladdorder_item(list);
		if(result>0){
			for(Order_item oi:list){
				Goods goods=iGoodsMapper.selectgoodsById(oi.getGoods_id()+"");
				if(goods!=null){
					Goods tempGoods= new Goods();
					tempGoods.setId(goods.getId());
					tempGoods.setNew_stock_amount(goods.getNew_stock_amount()-oi.getAmount());
					tempGoods.setSt_stock_amount(goods.getSt_stock_amount()-oi.getAmount());
					iGoodsMapper.updategoods(tempGoods);
				}
			}
		}
		return result;
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteOrder_item(String id){
		return iOrder_itemMapper.deleteorder_item(id);
	}

}

