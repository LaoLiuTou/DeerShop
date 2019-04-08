package com.deershop.service.area;
import java.util.List;
import java.util.Map;
import com.deershop.model.area.Area;
public interface IAreaService {
	/**
	* 通过id选取
	* @return
	*/
	public Area selectAreaById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Area> selectAreaByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountAreaByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateArea(Area area);

	/**
	* 添加 
	* @return
	*/ 
	public int addArea(Area area);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdArea(List<Area> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteArea(String id);

}

