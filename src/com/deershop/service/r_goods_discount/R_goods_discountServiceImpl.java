package com.deershop.service.r_goods_discount;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.r_goods_discount.IR_goods_discountMapper;
import com.deershop.model.r_goods_discount.R_goods_discount;
public class R_goods_discountServiceImpl  implements IR_goods_discountService {

	@Autowired
	private IR_goods_discountMapper iR_goods_discountMapper;
	/**
	* 通过id选取
	* @return
	*/
	public R_goods_discount selectR_goods_discountById(String id){
		return iR_goods_discountMapper.selectr_goods_discountById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_goods_discount> selectR_goods_discountByParam(Map paramMap){ 
		return iR_goods_discountMapper.selectr_goods_discountByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_goods_discountByParam(Map paramMap){ 
		return iR_goods_discountMapper.selectCountr_goods_discountByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateR_goods_discount(R_goods_discount r_goods_discount){
		return iR_goods_discountMapper.updater_goods_discount(r_goods_discount);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addR_goods_discount(R_goods_discount r_goods_discount){
		return iR_goods_discountMapper.addr_goods_discount(r_goods_discount);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdR_goods_discount(List<R_goods_discount> list){
		return iR_goods_discountMapper.muladdr_goods_discount(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteR_goods_discount(String id){
		return iR_goods_discountMapper.deleter_goods_discount(id);
	}

}

