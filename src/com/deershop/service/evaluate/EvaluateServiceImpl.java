package com.deershop.service.evaluate;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.evaluate.IEvaluateMapper;
import com.deershop.model.evaluate.Evaluate;
public class EvaluateServiceImpl  implements IEvaluateService {

	@Autowired
	private IEvaluateMapper iEvaluateMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Evaluate selectEvaluateById(String id){
		return iEvaluateMapper.selectevaluateById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Evaluate> selectEvaluateByParam(Map paramMap){ 
		return iEvaluateMapper.selectevaluateByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountEvaluateByParam(Map paramMap){ 
		return iEvaluateMapper.selectCountevaluateByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateEvaluate(Evaluate evaluate){
		return iEvaluateMapper.updateevaluate(evaluate);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addEvaluate(Evaluate evaluate){
		return iEvaluateMapper.addevaluate(evaluate);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdEvaluate(List<Evaluate> list){
		return iEvaluateMapper.muladdevaluate(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteEvaluate(String id){
		return iEvaluateMapper.deleteevaluate(id);
	}

}

