package com.deershop.service.send;
import java.util.List;
import java.util.Map;
import com.deershop.model.send.Send;
public interface ISendService {
	/**
	* 通过id选取
	* @return
	*/
	public Send selectSendById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Send> selectSendByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSendByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateSend(Send send);

	/**
	* 添加 
	* @return
	*/ 
	public int addSend(Send send);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdSend(List<Send> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteSend(String id);

}

