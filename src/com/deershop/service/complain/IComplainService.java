package com.deershop.service.complain;
import java.util.List;
import java.util.Map;
import com.deershop.model.complain.Complain;
public interface IComplainService {
	/**
	* 通过id选取
	* @return
	*/
	public Complain selectComplainById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Complain> selectComplainByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountComplainByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateComplain(Complain complain);

	/**
	* 添加 
	* @return
	*/ 
	public int addComplain(Complain complain);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdComplain(List<Complain> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteComplain(String id);

}

