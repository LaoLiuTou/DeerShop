package com.deershop.service.r_news_goods;
import java.util.List;
import java.util.Map;
import com.deershop.model.r_news_goods.R_news_goods;
public interface IR_news_goodsService {
	/**
	* 通过id选取
	* @return
	*/
	public R_news_goods selectR_news_goodsById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_news_goods> selectR_news_goodsByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_news_goodsByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateR_news_goods(R_news_goods r_news_goods);

	/**
	* 添加 
	* @return
	*/ 
	public int addR_news_goods(R_news_goods r_news_goods);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdR_news_goods(List<R_news_goods> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteR_news_goods(String id);
	public int deleteR_news_goodsByNewsId(String id);

}

