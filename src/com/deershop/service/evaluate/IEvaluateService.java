package com.deershop.service.evaluate;
import java.util.List;
import java.util.Map;
import com.deershop.model.evaluate.Evaluate;
public interface IEvaluateService {
	/**
	* 通过id选取
	* @return
	*/
	public Evaluate selectEvaluateById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Evaluate> selectEvaluateByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountEvaluateByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateEvaluate(Evaluate evaluate);

	/**
	* 添加 
	* @return
	*/ 
	public int addEvaluate(Evaluate evaluate);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdEvaluate(List<Evaluate> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteEvaluate(String id);

}

