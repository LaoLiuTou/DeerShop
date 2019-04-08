package com.deershop.service.seller;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.seller.ISellerMapper;
import com.deershop.model.seller.Seller;
public class SellerServiceImpl  implements ISellerService {

	@Autowired
	private ISellerMapper iSellerMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Seller selectSellerById(String id){
		return iSellerMapper.selectsellerById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Seller> selectSellerByParam(Map paramMap){ 
		return iSellerMapper.selectsellerByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSellerByParam(Map paramMap){ 
		return iSellerMapper.selectCountsellerByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateSeller(Seller seller){
		return iSellerMapper.updateseller(seller);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addSeller(Seller seller){
		return iSellerMapper.addseller(seller);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdSeller(List<Seller> list){
		return iSellerMapper.muladdseller(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteSeller(String id){
		return iSellerMapper.deleteseller(id);
	}

}

