package com.deershop.service.buyer_addr;
import java.util.List;
import java.util.Map;
import com.deershop.model.buyer_addr.Buyer_addr;
public interface IBuyer_addrService {
	/**
	* 通过id选取
	* @return
	*/
	public Buyer_addr selectBuyer_addrById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Buyer_addr> selectBuyer_addrByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountBuyer_addrByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateBuyer_addr(Buyer_addr buyer_addr);

	/**
	* 添加 
	* @return
	*/ 
	public int addBuyer_addr(Buyer_addr buyer_addr);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdBuyer_addr(List<Buyer_addr> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteBuyer_addr(String id);

}

