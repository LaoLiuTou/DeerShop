package com.deershop.service.r_buyer_promotion;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.r_buyer_promotion.IR_buyer_promotionMapper;
import com.deershop.model.r_buyer_promotion.R_buyer_promotion;
public class R_buyer_promotionServiceImpl  implements IR_buyer_promotionService {

	@Autowired
	private IR_buyer_promotionMapper iR_buyer_promotionMapper;
	/**
	* 通过id选取
	* @return
	*/
	public R_buyer_promotion selectR_buyer_promotionById(String id){
		return iR_buyer_promotionMapper.selectr_buyer_promotionById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_buyer_promotion> selectR_buyer_promotionByParam(Map paramMap){ 
		return iR_buyer_promotionMapper.selectr_buyer_promotionByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_buyer_promotionByParam(Map paramMap){ 
		return iR_buyer_promotionMapper.selectCountr_buyer_promotionByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateR_buyer_promotion(R_buyer_promotion r_buyer_promotion){
		return iR_buyer_promotionMapper.updater_buyer_promotion(r_buyer_promotion);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addR_buyer_promotion(R_buyer_promotion r_buyer_promotion){
		return iR_buyer_promotionMapper.addr_buyer_promotion(r_buyer_promotion);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdR_buyer_promotion(List<R_buyer_promotion> list){
		return iR_buyer_promotionMapper.muladdr_buyer_promotion(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteR_buyer_promotion(String id){
		return iR_buyer_promotionMapper.deleter_buyer_promotion(id);
	}

}

