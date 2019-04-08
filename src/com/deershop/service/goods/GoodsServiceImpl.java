package com.deershop.service.goods;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.goods.IGoodsMapper;
import com.deershop.dao.order_num.IOrder_numMapper;
import com.deershop.model.goods.Goods;
import com.deershop.model.order_item.Order_item;
import com.deershop.model.order_num.Order_num;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
public class GoodsServiceImpl  implements IGoodsService {

	@Autowired
	private IGoodsMapper iGoodsMapper;
	@Autowired
	private IOrder_numMapper iOrder_numMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Goods selectGoodsById(String id){
		return iGoodsMapper.selectgoodsById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Goods> selectGoodsByParam(Map paramMap){ 
		return iGoodsMapper.selectgoodsByParam(paramMap);
	}
	@SuppressWarnings("rawtypes")
	public List<Goods> selectGoodsByParamNews(Map paramMap){ 
		return iGoodsMapper.selectgoodsByParamNews(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountGoodsByParam(Map paramMap){ 
		return iGoodsMapper.selectCountgoodsByParam(paramMap);
	}
	@SuppressWarnings("rawtypes")
	public int selectCountGoodsByParamNews(Map paramMap){ 
		return iGoodsMapper.selectCountgoodsByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateGoods(Goods goods){
		Logger logger = Logger.getLogger("DeerShopLogger");
		if(goods!=null&&goods.getNew_stock_amount()!=null){
			logger.info("修改商品id："+goods.getId()+",数量:"+goods.getNew_stock_amount());
		}
		if(goods.getIs_on_shelf()!=null&&goods.getIs_on_shelf().equals("1")){
			goods.setOn_shelf_dt(new Date());
		}
		if(goods.getIs_on_shelf()!=null&&goods.getIs_on_shelf().equals("0")){
			goods.setUnder_shelf_dt(new Date());
		}
		return iGoodsMapper.updategoods(goods);
	}
	@Transactional
	public int mulupdateGoods(List<String> ids,String isHot){
		int result=0;
		for(String id:ids){
			Goods goods= new Goods();
			goods.setId(Long.parseLong(id));
			goods.setIs_hot(isHot);
			iGoodsMapper.updategoods(goods);
			result++;
		}
		
		return result;
	}
	/**
	* 添加 
	* @return
	*/ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public  int addGoods(Goods goods){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String now = sdf.format(new Date());
		int result =0;
		if(goods.getIs_on_shelf()!=null&&goods.getIs_on_shelf().equals("1")){
			goods.setOn_shelf_dt(new Date());
		}
		
		String codeNumber="0001";
		Map paramMap=new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("type","goods");  
		paramMap.put("up_dt",now); 
		List<Order_num> onList=iOrder_numMapper.selectorder_numByParam(paramMap);
		Order_num order_num= new Order_num();
		order_num.setNum(codeNumber);
		order_num.setType("goods");
		if(onList.size()>0){
			String cnum=onList.get(0).getNum();
			codeNumber=String.format("%04d", Integer.parseInt(cnum) + 1);
			order_num.setNum(codeNumber);
			order_num.setId(onList.get(0).getId());
			
		}
		
		//订单编号
		goods.setGoods_num(now.substring(2,now.length())+codeNumber);
		result=iGoodsMapper.addgoods(goods);
		if(result>0){
			//更新编号
			if(codeNumber.equals("0001")){
				iOrder_numMapper.addorder_num(order_num);
			}
			else{
				iOrder_numMapper.updateorder_num(order_num);
			}
		}
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdGoods(List<Goods> list){
		return iGoodsMapper.muladdgoods(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteGoods(String id){
		return iGoodsMapper.deletegoods(id);
	}

}

