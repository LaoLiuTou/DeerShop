package com.deershop.service.lov;
import java.util.List;
import java.util.Map;
import com.deershop.model.lov.Lov;
public interface ILovService {
	/**
	* 通过id选取
	* @return
	*/
	public Lov selectLovById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Lov> selectLovByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountLovByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateLov(Lov lov);

	/**
	* 添加 
	* @return
	*/ 
	public int addLov(Lov lov);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdLov(List<Lov> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteLov(String id);

}

