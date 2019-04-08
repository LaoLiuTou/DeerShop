package com.deershop.service.full_discount;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.full_discount.IFull_discountMapper;
import com.deershop.model.full_discount.Full_discount;
public class Full_discountServiceImpl  implements IFull_discountService {

	@Autowired
	private IFull_discountMapper iFull_discountMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Full_discount selectFull_discountById(String id){
		return iFull_discountMapper.selectfull_discountById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Full_discount> selectFull_discountByParam(Map paramMap){ 
		return iFull_discountMapper.selectfull_discountByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountFull_discountByParam(Map paramMap){ 
		return iFull_discountMapper.selectCountfull_discountByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateFull_discount(Full_discount full_discount){
		return iFull_discountMapper.updatefull_discount(full_discount);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addFull_discount(Full_discount full_discount){
		return iFull_discountMapper.addfull_discount(full_discount);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdFull_discount(List<Full_discount> list){
		return iFull_discountMapper.muladdfull_discount(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteFull_discount(String id){
		return iFull_discountMapper.deletefull_discount(id);
	}

}

