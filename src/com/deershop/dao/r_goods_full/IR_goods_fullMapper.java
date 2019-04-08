package com.deershop.dao.r_goods_full;
import java.util.List;
import java.util.Map;
import com.deershop.model.r_goods_full.R_goods_full;
	public interface IR_goods_fullMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public R_goods_full selectr_goods_fullById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<R_goods_full> selectr_goods_fullByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountr_goods_fullByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updater_goods_full(R_goods_full r_goods_full);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addr_goods_full(R_goods_full r_goods_full);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdr_goods_full(List<R_goods_full> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleter_goods_full(String id);

}

