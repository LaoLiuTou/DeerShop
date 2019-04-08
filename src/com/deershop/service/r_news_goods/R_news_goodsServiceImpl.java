package com.deershop.service.r_news_goods;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.r_news_goods.IR_news_goodsMapper;
import com.deershop.model.r_news_goods.R_news_goods;
public class R_news_goodsServiceImpl  implements IR_news_goodsService {

	@Autowired
	private IR_news_goodsMapper iR_news_goodsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public R_news_goods selectR_news_goodsById(String id){
		return iR_news_goodsMapper.selectr_news_goodsById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<R_news_goods> selectR_news_goodsByParam(Map paramMap){ 
		return iR_news_goodsMapper.selectr_news_goodsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountR_news_goodsByParam(Map paramMap){ 
		return iR_news_goodsMapper.selectCountr_news_goodsByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateR_news_goods(R_news_goods r_news_goods){
		return iR_news_goodsMapper.updater_news_goods(r_news_goods);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addR_news_goods(R_news_goods r_news_goods){
		return iR_news_goodsMapper.addr_news_goods(r_news_goods);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdR_news_goods(List<R_news_goods> list){
		return iR_news_goodsMapper.muladdr_news_goods(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteR_news_goods(String id){
		return iR_news_goodsMapper.deleter_news_goods(id);
	}
	@Transactional
	public  int deleteR_news_goodsByNewsId(String newsId){
		return iR_news_goodsMapper.deleter_news_goodsByNewsId(newsId);
	}

}

