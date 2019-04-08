package com.deershop.service.banner;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.banner.IBannerMapper;
import com.deershop.dao.goods.IGoodsMapper;
import com.deershop.model.banner.Banner;
import com.deershop.model.goods.Goods;
public class BannerServiceImpl  implements IBannerService {

	@Autowired
	private IBannerMapper iBannerMapper;
	@Autowired
	private IGoodsMapper iGoodsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Banner selectBannerById(String id){
		return iBannerMapper.selectbannerById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Banner> selectBannerByParam(Map paramMap){ 
		return iBannerMapper.selectbannerByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountBannerByParam(Map paramMap){ 
		return iBannerMapper.selectCountbannerByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateBanner(Banner banner){
		return iBannerMapper.updatebanner(banner);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addBanner(Banner banner){
		int result=0;
		result=iBannerMapper.addbanner(banner);
		if(result>0&&banner.getGoods_id()!=null){
			Goods goods = new Goods();
			goods.setId(banner.getGoods_id());
			goods.setIs_main("1");
			iGoodsMapper.updategoods(goods);
		}
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdBanner(List<Banner> list){
		return iBannerMapper.muladdbanner(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteBanner(String id){
		
		int result=0;
		Banner banner = iBannerMapper.selectbannerById(id);
		result=iBannerMapper.deletebanner(id);
		if(result>0&&banner.getGoods_id()!=null){
			Goods goods = new Goods();
			goods.setId(banner.getGoods_id());
			goods.setIs_main("0");
			iGoodsMapper.updategoods(goods);
		}
		return result;
		 
	}

}

