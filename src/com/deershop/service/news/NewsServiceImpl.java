package com.deershop.service.news;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.news.INewsMapper;
import com.deershop.dao.r_news_goods.IR_news_goodsMapper;
import com.deershop.model.news.News;
import com.deershop.model.r_news_goods.R_news_goods;
public class NewsServiceImpl  implements INewsService {

	@Autowired
	private INewsMapper iNewsMapper;
	@Autowired
	private IR_news_goodsMapper iR_news_goodsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public News selectNewsById(String id){
		return iNewsMapper.selectnewsById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<News> selectNewsByParam(Map paramMap){ 
		return iNewsMapper.selectnewsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountNewsByParam(Map paramMap){ 
		return iNewsMapper.selectCountnewsByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateNews(News news,String goods_ids){
		int result=0;
		result=iNewsMapper.updatenews(news);
		if(result>0&&goods_ids!=null){
			iR_news_goodsMapper.deleter_news_goodsByNewsId(news.getId()+"");
			String[] goods=goods_ids.split(",");
			for(String id:goods){
				R_news_goods r_news_goods= new R_news_goods();
				r_news_goods.setGoods_id(Long.parseLong(id));
				r_news_goods.setNews_id(news.getId());
				r_news_goods.setC_id(news.getC_id());
				iR_news_goodsMapper.addr_news_goods(r_news_goods);
			}
		}
		
		return result;
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addNews(News news,String goods_ids){
		int result=0;
		if(news.getStatus()!=null&&news.getStatus().equals("1")){
			news.setPublish_dt(new Date());
		}
		result=iNewsMapper.addnews(news);
		if(result>0&&goods_ids!=null){
			String[] goods=goods_ids.split(",");
			for(String id:goods){
				R_news_goods r_news_goods= new R_news_goods();
				r_news_goods.setGoods_id(Long.parseLong(id));
				r_news_goods.setNews_id(news.getId());
				r_news_goods.setC_id(news.getC_id());
				iR_news_goodsMapper.addr_news_goods(r_news_goods);
			}
		}
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdNews(List<News> list){
		return iNewsMapper.muladdnews(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteNews(String id){
		return iNewsMapper.deletenews(id);
	}

}

