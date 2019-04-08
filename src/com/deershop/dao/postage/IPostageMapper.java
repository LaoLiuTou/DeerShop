package com.deershop.dao.postage;
import java.util.List;
import java.util.Map;
import com.deershop.model.postage.Postage;
	public interface IPostageMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Postage selectpostageById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Postage> selectpostageByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountpostageByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatepostage(Postage postage);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addpostage(Postage postage);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdpostage(List<Postage> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletepostage(String id);

}

