package com.deershop.service.buyer;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.buyer.IBuyerMapper;
import com.deershop.model.buyer.Buyer;
public class BuyerServiceImpl  implements IBuyerService {

	@Autowired
	private IBuyerMapper iBuyerMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Buyer selectBuyerById(String id){
		return iBuyerMapper.selectbuyerById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Buyer> selectBuyerByParam(Map paramMap){ 
		return iBuyerMapper.selectbuyerByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountBuyerByParam(Map paramMap){ 
		return iBuyerMapper.selectCountbuyerByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateBuyer(Buyer buyer){
		return iBuyerMapper.updatebuyer(buyer);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addBuyer(Buyer buyer){
		return iBuyerMapper.addbuyer(buyer);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdBuyer(List<Buyer> list){
		return iBuyerMapper.muladdbuyer(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteBuyer(String id){
		return iBuyerMapper.deletebuyer(id);
	}

}

