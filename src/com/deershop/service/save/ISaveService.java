package com.deershop.service.save;
import java.util.List;
import java.util.Map;
import com.deershop.model.save.Save;
public interface ISaveService {
	/**
	* 通过id选取
	* @return
	*/
	public Save selectSaveById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Save> selectSaveByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSaveByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateSave(Save save);

	/**
	* 添加 
	* @return
	*/ 
	public int addSave(Save save);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdSave(List<Save> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteSave(String id);

}

