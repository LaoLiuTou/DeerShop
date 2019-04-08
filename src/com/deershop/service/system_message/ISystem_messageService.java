package com.deershop.service.system_message;
import java.util.List;
import java.util.Map;
import com.deershop.model.system_message.System_message;
public interface ISystem_messageService {
	/**
	* 通过id选取
	* @return
	*/
	public System_message selectSystem_messageById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<System_message> selectSystem_messageByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSystem_messageByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateSystem_message(System_message system_message);

	/**
	* 添加 
	* @return
	*/ 
	public int addSystem_message(System_message system_message);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdSystem_message(List<System_message> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteSystem_message(String id);

}

