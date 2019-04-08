package com.deershop.service.seller_card;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.seller_card.ISeller_cardMapper;
import com.deershop.model.seller_card.Seller_card;
public class Seller_cardServiceImpl  implements ISeller_cardService {

	@Autowired
	private ISeller_cardMapper iSeller_cardMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Seller_card selectSeller_cardById(String id){
		return iSeller_cardMapper.selectseller_cardById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Seller_card> selectSeller_cardByParam(Map paramMap){ 
		return iSeller_cardMapper.selectseller_cardByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSeller_cardByParam(Map paramMap){ 
		return iSeller_cardMapper.selectCountseller_cardByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateSeller_card(Seller_card seller_card){
		return iSeller_cardMapper.updateseller_card(seller_card);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addSeller_card(Seller_card seller_card){
		return iSeller_cardMapper.addseller_card(seller_card);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdSeller_card(List<Seller_card> list){
		return iSeller_cardMapper.muladdseller_card(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteSeller_card(String id){
		return iSeller_cardMapper.deleteseller_card(id);
	}

}

