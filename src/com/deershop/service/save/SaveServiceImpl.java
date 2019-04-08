package com.deershop.service.save;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.save.ISaveMapper;
import com.deershop.model.save.Save;
public class SaveServiceImpl  implements ISaveService {

	@Autowired
	private ISaveMapper iSaveMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Save selectSaveById(String id){
		return iSaveMapper.selectsaveById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Save> selectSaveByParam(Map paramMap){ 
		return iSaveMapper.selectsaveByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSaveByParam(Map paramMap){ 
		return iSaveMapper.selectCountsaveByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateSave(Save save){
		return iSaveMapper.updatesave(save);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addSave(Save save){
		return iSaveMapper.addsave(save);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdSave(List<Save> list){
		return iSaveMapper.muladdsave(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteSave(String id){
		return iSaveMapper.deletesave(id);
	}

}

