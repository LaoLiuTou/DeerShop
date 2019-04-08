package com.deershop.dao.area;
import java.util.List;
import java.util.Map;
import com.deershop.model.area.Area;
	public interface IAreaMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Area selectareaById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Area> selectareaByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountareaByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatearea(Area area);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addarea(Area area);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdarea(List<Area> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletearea(String id);

}

