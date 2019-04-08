package com.deershop.service.complain;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.complain.IComplainMapper;
import com.deershop.model.complain.Complain;
public class ComplainServiceImpl  implements IComplainService {

	@Autowired
	private IComplainMapper iComplainMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Complain selectComplainById(String id){
		return iComplainMapper.selectcomplainById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Complain> selectComplainByParam(Map paramMap){ 
		return iComplainMapper.selectcomplainByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountComplainByParam(Map paramMap){ 
		return iComplainMapper.selectCountcomplainByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateComplain(Complain complain){
		return iComplainMapper.updatecomplain(complain);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addComplain(Complain complain){
		return iComplainMapper.addcomplain(complain);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdComplain(List<Complain> list){
		return iComplainMapper.muladdcomplain(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteComplain(String id){
		return iComplainMapper.deletecomplain(id);
	}

}

