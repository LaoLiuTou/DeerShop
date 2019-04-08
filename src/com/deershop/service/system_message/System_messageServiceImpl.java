package com.deershop.service.system_message;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.system_message.ISystem_messageMapper;
import com.deershop.model.system_message.System_message;
public class System_messageServiceImpl  implements ISystem_messageService {

	@Autowired
	private ISystem_messageMapper iSystem_messageMapper;
	/**
	* 通过id选取
	* @return
	*/
	public System_message selectSystem_messageById(String id){
		return iSystem_messageMapper.selectsystem_messageById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<System_message> selectSystem_messageByParam(Map paramMap){ 
		return iSystem_messageMapper.selectsystem_messageByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSystem_messageByParam(Map paramMap){ 
		return iSystem_messageMapper.selectCountsystem_messageByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateSystem_message(System_message system_message){
		return iSystem_messageMapper.updatesystem_message(system_message);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addSystem_message(System_message system_message){
		return iSystem_messageMapper.addsystem_message(system_message);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdSystem_message(List<System_message> list){
		return iSystem_messageMapper.muladdsystem_message(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteSystem_message(String id){
		return iSystem_messageMapper.deletesystem_message(id);
	}

}

