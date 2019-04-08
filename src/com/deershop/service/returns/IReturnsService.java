package com.deershop.service.returns;
import java.util.List;
import java.util.Map;
import com.deershop.model.returns.Returns;
public interface IReturnsService {
	/**
	* 通过id选取
	* @return
	*/
	public Returns selectReturnsById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Returns> selectReturnsByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountReturnsByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateReturns(Returns returns);

	/**
	* 添加 
	* @return
	*/ 
	public int addReturns(Returns returns);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdReturns(List<Returns> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteReturns(String id);

}

