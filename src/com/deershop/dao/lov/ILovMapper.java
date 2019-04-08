package com.deershop.dao.lov;
import java.util.List;
import java.util.Map;
import com.deershop.model.lov.Lov;
	public interface ILovMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Lov selectlovById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Lov> selectlovByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountlovByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatelov(Lov lov);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addlov(Lov lov);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdlov(List<Lov> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletelov(String id);

}

