package com.deershop.service.r_goods_full;
import java.util.List;
import java.util.Map;
import com.deershop.model.r_goods_full.R_goods_full;
public interface IR_goods_fullService {
	/**
	* 通过id选取
	* @return
	*/
	public R_goods_full selectR_goods_fullById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_goods_full> selectR_goods_fullByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_goods_fullByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateR_goods_full(R_goods_full r_goods_full);

	/**
	* 添加 
	* @return
	*/ 
	public int addR_goods_full(R_goods_full r_goods_full);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdR_goods_full(List<R_goods_full> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteR_goods_full(String id);

}

