package com.deershop.service.r_goods_full;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.r_goods_full.IR_goods_fullMapper;
import com.deershop.model.r_goods_full.R_goods_full;
public class R_goods_fullServiceImpl  implements IR_goods_fullService {

	@Autowired
	private IR_goods_fullMapper iR_goods_fullMapper;
	/**
	* 通过id选取
	* @return
	*/
	public R_goods_full selectR_goods_fullById(String id){
		return iR_goods_fullMapper.selectr_goods_fullById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_goods_full> selectR_goods_fullByParam(Map paramMap){ 
		return iR_goods_fullMapper.selectr_goods_fullByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_goods_fullByParam(Map paramMap){ 
		return iR_goods_fullMapper.selectCountr_goods_fullByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateR_goods_full(R_goods_full r_goods_full){
		return iR_goods_fullMapper.updater_goods_full(r_goods_full);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addR_goods_full(R_goods_full r_goods_full){
		return iR_goods_fullMapper.addr_goods_full(r_goods_full);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdR_goods_full(List<R_goods_full> list){
		return iR_goods_fullMapper.muladdr_goods_full(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteR_goods_full(String id){
		return iR_goods_fullMapper.deleter_goods_full(id);
	}

}

