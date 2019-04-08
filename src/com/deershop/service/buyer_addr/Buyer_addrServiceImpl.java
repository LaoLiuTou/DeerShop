package com.deershop.service.buyer_addr;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.buyer_addr.IBuyer_addrMapper;
import com.deershop.model.buyer_addr.Buyer_addr;
public class Buyer_addrServiceImpl  implements IBuyer_addrService {

	@Autowired
	private IBuyer_addrMapper iBuyer_addrMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Buyer_addr selectBuyer_addrById(String id){
		return iBuyer_addrMapper.selectbuyer_addrById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Buyer_addr> selectBuyer_addrByParam(Map paramMap){ 
		return iBuyer_addrMapper.selectbuyer_addrByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountBuyer_addrByParam(Map paramMap){ 
		return iBuyer_addrMapper.selectCountbuyer_addrByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateBuyer_addr(Buyer_addr buyer_addr){
		return iBuyer_addrMapper.updatebuyer_addr(buyer_addr);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addBuyer_addr(Buyer_addr buyer_addr){
		return iBuyer_addrMapper.addbuyer_addr(buyer_addr);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdBuyer_addr(List<Buyer_addr> list){
		return iBuyer_addrMapper.muladdbuyer_addr(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteBuyer_addr(String id){
		return iBuyer_addrMapper.deletebuyer_addr(id);
	}

}

