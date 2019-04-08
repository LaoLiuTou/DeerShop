package com.deershop.service.area;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.deershop.dao.area.IAreaMapper;
import com.deershop.model.area.Area;
public class AreaServiceImpl  implements IAreaService {

	@Autowired
	private IAreaMapper iAreaMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Area selectAreaById(String id){
		return iAreaMapper.selectareaById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Area> selectAreaByParam(Map paramMap){ 
		return iAreaMapper.selectareaByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountAreaByParam(Map paramMap){ 
		return iAreaMapper.selectCountareaByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateArea(Area area){
		return iAreaMapper.updatearea(area);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addArea(Area area){
		return iAreaMapper.addarea(area);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdArea(List<Area> list){
		return iAreaMapper.muladdarea(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteArea(String id){
		return iAreaMapper.deletearea(id);
	}

}

