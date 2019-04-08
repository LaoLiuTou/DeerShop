package com.deershop.dao.system_message;
import java.util.List;
import java.util.Map;
import com.deershop.model.system_message.System_message;
	public interface ISystem_messageMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public System_message selectsystem_messageById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<System_message> selectsystem_messageByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountsystem_messageByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatesystem_message(System_message system_message);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addsystem_message(System_message system_message);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdsystem_message(List<System_message> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletesystem_message(String id);

}

