package com.deershop.dao.complain;
import java.util.List;
import java.util.Map;
import com.deershop.model.complain.Complain;
	public interface IComplainMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Complain selectcomplainById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Complain> selectcomplainByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountcomplainByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatecomplain(Complain complain);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addcomplain(Complain complain);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdcomplain(List<Complain> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletecomplain(String id);

}

