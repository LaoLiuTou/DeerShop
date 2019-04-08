package com.deershop.service.goods_category;
import java.util.List;
import java.util.Map;
import com.deershop.model.goods_category.Goods_category;
public interface IGoods_categoryService {
	/**
	* 通过id选取
	* @return
	*/
	public Goods_category selectGoods_categoryById(String id);
	
	public List<Goods_category> selectgoods_categoryByPid(String  pid); 

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Goods_category> selectGoods_categoryByParam(Map paramMap); 
	@SuppressWarnings("rawtypes")
	public List<Goods_category> treeGoods_categoryByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountGoods_categoryByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateGoods_category(Goods_category goods_category);
	public int updateGoods_categoryOrder(List<String> list);
	public int updateGoods_categoryDeer(List<String> list);

	/**
	* 添加 
	* @return
	*/ 
	public int addGoods_category(Goods_category goods_category);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdGoods_category(List<Goods_category> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteGoods_category(String id);

}

