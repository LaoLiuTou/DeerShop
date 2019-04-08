package com.deershop.service.promotion_code;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.promotion_code.IPromotion_codeMapper;
import com.deershop.model.promotion_code.Promotion_code;
public class Promotion_codeServiceImpl  implements IPromotion_codeService {

	@Autowired
	private IPromotion_codeMapper iPromotion_codeMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Promotion_code selectPromotion_codeById(String id){
		return iPromotion_codeMapper.selectpromotion_codeById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Promotion_code> selectPromotion_codeByParam(Map paramMap){ 
		return iPromotion_codeMapper.selectpromotion_codeByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountPromotion_codeByParam(Map paramMap){ 
		return iPromotion_codeMapper.selectCountpromotion_codeByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updatePromotion_code(Promotion_code promotion_code){
		return iPromotion_codeMapper.updatepromotion_code(promotion_code);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addPromotion_code(Promotion_code promotion_code){
		return iPromotion_codeMapper.addpromotion_code(promotion_code);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdPromotion_code(List<Promotion_code> list){
		return iPromotion_codeMapper.muladdpromotion_code(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deletePromotion_code(String id){
		return iPromotion_codeMapper.deletepromotion_code(id);
	}

}

