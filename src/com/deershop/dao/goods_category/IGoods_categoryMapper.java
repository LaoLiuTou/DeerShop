package com.deershop.dao.goods_category;
import java.util.List;
import java.util.Map;
import com.deershop.model.goods_category.Goods_category;
	public interface IGoods_categoryMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Goods_category selectgoods_categoryById(String id);
	
	public List<Goods_category> selectgoods_categoryByPid(String pid); 
	
	/**
 	* 通过查询参数获取信息
 	* @return
	 */ 
	 @SuppressWarnings("rawtypes")
	public List<Goods_category> selectgoods_categoryByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountgoods_categoryByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updategoods_category(Goods_category goods_category);
	public  int updategoods_categorydeer(Goods_category goods_category);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addgoods_category(Goods_category goods_category);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdgoods_category(List<Goods_category> list);
	/**
 	* 删除 
 	* @return 
	 */ 
	public  int deletegoods_category(String id);
	/**
	 * 删除 
	 * @return 
	 */ 
	public  int deletegoods_categoryByPid(String pid);

}

