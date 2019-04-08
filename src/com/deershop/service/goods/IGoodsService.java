package com.deershop.service.goods;
import java.util.List;
import java.util.Map;
import com.deershop.model.goods.Goods;
public interface IGoodsService {
	/**
	* 通过id选取
	* @return
	*/
	public Goods selectGoodsById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Goods> selectGoodsByParam(Map paramMap); 
	@SuppressWarnings("rawtypes")
	public List<Goods> selectGoodsByParamNews(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountGoodsByParam(Map paramMap); 
	public int selectCountGoodsByParamNews(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateGoods(Goods goods);
	public int mulupdateGoods(List<String> ids,String isHot);

	/**
	* 添加 
	* @return
	*/ 
	public int addGoods(Goods goods);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdGoods(List<Goods> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteGoods(String id);

}

