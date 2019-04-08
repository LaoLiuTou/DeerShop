package com.deershop.service.lov;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.lov.ILovMapper;
import com.deershop.model.lov.Lov;
public class LovServiceImpl  implements ILovService {

	@Autowired
	private ILovMapper iLovMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Lov selectLovById(String id){
		return iLovMapper.selectlovById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Lov> selectLovByParam(Map paramMap){ 
		return iLovMapper.selectlovByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountLovByParam(Map paramMap){ 
		return iLovMapper.selectCountlovByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateLov(Lov lov){
		return iLovMapper.updatelov(lov);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addLov(Lov lov){
		return iLovMapper.addlov(lov);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdLov(List<Lov> list){
		return iLovMapper.muladdlov(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteLov(String id){
		return iLovMapper.deletelov(id);
	}

}

