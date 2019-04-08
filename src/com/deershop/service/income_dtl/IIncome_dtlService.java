package com.deershop.service.income_dtl;
import java.util.List;
import java.util.Map;
import com.deershop.model.income_dtl.Income_dtl;
public interface IIncome_dtlService {
	/**
	* 通过id选取
	* @return
	*/
	public Income_dtl selectIncome_dtlById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Income_dtl> selectIncome_dtlByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountIncome_dtlByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateIncome_dtl(Income_dtl income_dtl);

	/**
	* 添加 
	* @return
	*/ 
	public int addIncome_dtl(Income_dtl income_dtl);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdIncome_dtl(List<Income_dtl> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteIncome_dtl(String id);

}

