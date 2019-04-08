package com.deershop.dao.buyer_addr;
import java.util.List;
import java.util.Map;
import com.deershop.model.buyer_addr.Buyer_addr;
	public interface IBuyer_addrMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Buyer_addr selectbuyer_addrById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Buyer_addr> selectbuyer_addrByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountbuyer_addrByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatebuyer_addr(Buyer_addr buyer_addr);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addbuyer_addr(Buyer_addr buyer_addr);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdbuyer_addr(List<Buyer_addr> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletebuyer_addr(String id);

}

