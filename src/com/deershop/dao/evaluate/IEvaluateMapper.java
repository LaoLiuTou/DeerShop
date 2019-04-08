package com.deershop.dao.evaluate;
import java.util.List;
import java.util.Map;
import com.deershop.model.evaluate.Evaluate;
	public interface IEvaluateMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Evaluate selectevaluateById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Evaluate> selectevaluateByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountevaluateByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateevaluate(Evaluate evaluate);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addevaluate(Evaluate evaluate);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdevaluate(List<Evaluate> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteevaluate(String id);

}

