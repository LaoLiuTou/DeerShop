package com.deershop.service.postage;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.postage.IPostageMapper;
import com.deershop.model.postage.Postage;
public class PostageServiceImpl  implements IPostageService {

	@Autowired
	private IPostageMapper iPostageMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Postage selectPostageById(String id){
		return iPostageMapper.selectpostageById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Postage> selectPostageByParam(Map paramMap){ 
		return iPostageMapper.selectpostageByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountPostageByParam(Map paramMap){ 
		return iPostageMapper.selectCountpostageByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updatePostage(Postage postage){
		return iPostageMapper.updatepostage(postage);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addPostage(Postage postage){
		return iPostageMapper.addpostage(postage);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdPostage(List<Postage> list){
		return iPostageMapper.muladdpostage(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deletePostage(String id){
		return iPostageMapper.deletepostage(id);
	}

}

