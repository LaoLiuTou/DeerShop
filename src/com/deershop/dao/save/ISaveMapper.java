package com.deershop.dao.save;
import java.util.List;
import java.util.Map;
import com.deershop.model.save.Save;
	public interface ISaveMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Save selectsaveById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Save> selectsaveByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountsaveByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatesave(Save save);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addsave(Save save);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdsave(List<Save> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletesave(String id);

}

