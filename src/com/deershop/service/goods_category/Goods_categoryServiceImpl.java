package com.deershop.service.goods_category;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.goods_category.IGoods_categoryMapper;
import com.deershop.model.goods_category.Goods_category;
public class Goods_categoryServiceImpl  implements IGoods_categoryService {

	@Autowired
	private IGoods_categoryMapper iGoods_categoryMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Goods_category selectGoods_categoryById(String id){
		return iGoods_categoryMapper.selectgoods_categoryById(id);
	}
	
	public List<Goods_category> selectgoods_categoryByPid(String pid){ 
		return iGoods_categoryMapper.selectgoods_categoryByPid(pid);
	}

	
	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Goods_category> selectGoods_categoryByParam(Map paramMap){ 
		return iGoods_categoryMapper.selectgoods_categoryByParam(paramMap);
	}
	@SuppressWarnings("rawtypes")
	@Transactional
	public List<Goods_category> treeGoods_categoryByParam(Map paramMap){ 
		List<Goods_category> list= iGoods_categoryMapper.selectgoods_categoryByParam(paramMap);

		for(Goods_category gc:list){
			List<Goods_category> subList=iGoods_categoryMapper.selectgoods_categoryByPid(gc.getId()+"");
			gc.setSubcategory(subList);
		}
		
		return list;
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountGoods_categoryByParam(Map paramMap){ 
		return iGoods_categoryMapper.selectCountgoods_categoryByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateGoods_category(Goods_category goods_category){
		return iGoods_categoryMapper.updategoods_category(goods_category);
	}
	@Transactional
	public  int updateGoods_categoryOrder(List<String> list){
		int result=0;
		for(int index=1;index<=list.size();index++){
			Goods_category gc = new Goods_category();
			gc.setId(Long.parseLong(list.get(index-1)));
			gc.setSeq(Long.parseLong(index+""));
			iGoods_categoryMapper.updategoods_category(gc);
		}
		return result;
	}
	@Transactional
	public  int updateGoods_categoryDeer(List<String> list){
		int result=0;
		Goods_category temp =new Goods_category();
		temp.setDeer_flag("0");
		iGoods_categoryMapper.updategoods_categorydeer(temp);
		for(int index=0;index<list.size();index++){
			Goods_category gc = new Goods_category();
			gc.setId(Long.parseLong(list.get(index)));
			gc.setDeer_flag("1");
			iGoods_categoryMapper.updategoods_category(gc);
		}
		return result;
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addGoods_category(Goods_category goods_category){
		return iGoods_categoryMapper.addgoods_category(goods_category);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdGoods_category(List<Goods_category> list){
		return iGoods_categoryMapper.muladdgoods_category(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteGoods_category(String id){
		int result=0;
		result=iGoods_categoryMapper.deletegoods_category(id);
		if(result>0){
			iGoods_categoryMapper.deletegoods_categoryByPid(id);
		}
		return iGoods_categoryMapper.deletegoods_category(id);
	}

}

