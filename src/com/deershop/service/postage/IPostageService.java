package com.deershop.service.postage;
import java.util.List;
import java.util.Map;
import com.deershop.model.postage.Postage;
public interface IPostageService {
	/**
	* 通过id选取
	* @return
	*/
	public Postage selectPostageById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Postage> selectPostageByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountPostageByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updatePostage(Postage postage);

	/**
	* 添加 
	* @return
	*/ 
	public int addPostage(Postage postage);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdPostage(List<Postage> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deletePostage(String id);

}

