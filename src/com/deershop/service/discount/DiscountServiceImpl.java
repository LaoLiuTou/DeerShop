package com.deershop.service.discount;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.discount.IDiscountMapper;
import com.deershop.model.discount.Discount;
public class DiscountServiceImpl  implements IDiscountService {

	@Autowired
	private IDiscountMapper iDiscountMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Discount selectDiscountById(String id){
		return iDiscountMapper.selectdiscountById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Discount> selectDiscountByParam(Map paramMap){ 
		return iDiscountMapper.selectdiscountByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountDiscountByParam(Map paramMap){ 
		return iDiscountMapper.selectCountdiscountByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateDiscount(Discount discount){
		return iDiscountMapper.updatediscount(discount);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addDiscount(Discount discount){
		return iDiscountMapper.adddiscount(discount);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdDiscount(List<Discount> list){
		return iDiscountMapper.muladddiscount(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteDiscount(String id){
		return iDiscountMapper.deletediscount(id);
	}

}

