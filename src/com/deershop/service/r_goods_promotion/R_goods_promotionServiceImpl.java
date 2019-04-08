package com.deershop.service.r_goods_promotion;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.r_goods_promotion.IR_goods_promotionMapper;
import com.deershop.model.r_goods_promotion.R_goods_promotion;
public class R_goods_promotionServiceImpl  implements IR_goods_promotionService {

	@Autowired
	private IR_goods_promotionMapper iR_goods_promotionMapper;
	/**
	* 通过id选取
	* @return
	*/
	public R_goods_promotion selectR_goods_promotionById(String id){
		return iR_goods_promotionMapper.selectr_goods_promotionById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_goods_promotion> selectR_goods_promotionByParam(Map paramMap){ 
		return iR_goods_promotionMapper.selectr_goods_promotionByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_goods_promotionByParam(Map paramMap){ 
		return iR_goods_promotionMapper.selectCountr_goods_promotionByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateR_goods_promotion(R_goods_promotion r_goods_promotion){
		return iR_goods_promotionMapper.updater_goods_promotion(r_goods_promotion);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addR_goods_promotion(R_goods_promotion r_goods_promotion){
		return iR_goods_promotionMapper.addr_goods_promotion(r_goods_promotion);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdR_goods_promotion(List<R_goods_promotion> list){
		return iR_goods_promotionMapper.muladdr_goods_promotion(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteR_goods_promotion(String id){
		return iR_goods_promotionMapper.deleter_goods_promotion(id);
	}

}

