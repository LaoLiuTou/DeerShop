package com.deershop.dao.income_dtl;
import java.util.List;
import java.util.Map;
import com.deershop.model.income_dtl.Income_dtl;
	public interface IIncome_dtlMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Income_dtl selectincome_dtlById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Income_dtl> selectincome_dtlByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountincome_dtlByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateincome_dtl(Income_dtl income_dtl);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addincome_dtl(Income_dtl income_dtl);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdincome_dtl(List<Income_dtl> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteincome_dtl(String id);

}

