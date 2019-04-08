package com.deershop.controller.goods_category;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.deershop.service.goods_category.IGoods_categoryService;
import com.deershop.model.goods_category.Goods_category;
@Controller
public class Goods_categoryController {
	@Autowired
	private IGoods_categoryService iGoods_categoryService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addGoods_category")
	@ResponseBody
	public Map add(Goods_category goods_category){
		Map resultMap=new HashMap();
		try {
			Map paramMap=new HashMap();
			paramMap.put("ctg_name",goods_category.getCtg_name());
			int totalnumber=iGoods_categoryService.selectCountGoods_categoryByParam(paramMap);
			if(totalnumber>0){
				resultMap.put("status", "-1");
				resultMap.put("msg", "该分类已存在！");
			}
			else{
				iGoods_categoryService.addGoods_category(goods_category);
				resultMap.put("status", "0");
				resultMap.put("msg", goods_category.getId());
				logger.info("新建成功，主键："+goods_category.getId());
			}
			
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdGoods_category")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Goods_category goods_category){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Goods_category.class);
			List<Goods_category> list = (List<Goods_category>)objectMapper.readValue(data, javaType);
			iGoods_categoryService.muladdGoods_category(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+goods_category.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteGoods_category")
	@ResponseBody
	public Map delete(Goods_category goods_category){
		Map resultMap=new HashMap();
		try {
			if(goods_category.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iGoods_categoryService.deleteGoods_category(goods_category.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+goods_category.getId());
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "删除失败！");
			logger.info("删除失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectGoods_category")
	@ResponseBody
	public Map select(Goods_category goods_category){
		Map resultMap=new HashMap();
		try {
			if(goods_category.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Goods_category resultSelect=iGoods_categoryService.selectGoods_categoryById(goods_category.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", resultSelect);
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/updateGoods_category")
	@ResponseBody
	public Map update(Goods_category goods_category){
		Map resultMap=new HashMap();
		try {
			if(goods_category.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iGoods_categoryService.updateGoods_category(goods_category);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+goods_category.getId());
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "更新失败！");
			logger.info("更新失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/updateGoods_categoryOrder")
	@ResponseBody
	public Map updateOrder(HttpServletRequest request){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
			List<String> list = (List<String>)objectMapper.readValue(data, javaType);
			iGoods_categoryService.updateGoods_categoryOrder(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "更新成功！");
			logger.info("更新成功，主键："+data);
			 
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "更新失败！");
			logger.info("更新失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deerGoods_category")
	@ResponseBody
	public Map updateDeer(HttpServletRequest request){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
			List<String> list = (List<String>)objectMapper.readValue(data, javaType);
			iGoods_categoryService.updateGoods_categoryDeer(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "更新成功！");
			logger.info("更新成功，主键："+data);
			
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "更新失败！");
			logger.info("更新失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listGoods_category")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Goods_category goods_category)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String page=request.getParameter("page");
			String size=request.getParameter("size");
			if(page!=null&&size!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",(Integer.parseInt(page)-1)*Integer.parseInt(size));
				paramMap.put("toPage",Integer.parseInt(size)); 
				String orderBy=request.getParameter("orderBy");
				if(orderBy!=null){
					paramMap.put("orderBy",orderBy); 
				}
				else{
					paramMap.put("orderBy","ID DESC"); 
				}
				paramMap.put("seq",goods_category.getSeq());
				paramMap.put("id",goods_category.getId());
				paramMap.put("pid",goods_category.getPid());
				paramMap.put("ctg_name",goods_category.getCtg_name());
				paramMap.put("status",goods_category.getStatus());
				paramMap.put("deer_flag", goods_category.getDeer_flag());
				String cd_dtFrom=request.getParameter("cd_dtFrom");
				String cd_dtTo=request.getParameter("cd_dtTo");
				if(cd_dtFrom!=null&&!cd_dtFrom.equals(""))
				paramMap.put("cd_dtFrom", sdf.parse(cd_dtFrom));
				if(cd_dtTo!=null&&!cd_dtTo.equals(""))
				paramMap.put("cd_dtTo", sdf.parse(cd_dtTo));
				String up_dtFrom=request.getParameter("up_dtFrom");
				String up_dtTo=request.getParameter("up_dtTo");
				if(up_dtFrom!=null&&!up_dtFrom.equals(""))
				paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
				if(up_dtTo!=null&&!up_dtTo.equals(""))
				paramMap.put("up_dtTo", sdf.parse(up_dtTo));
				String pidisnull=request.getParameter("pidisnull");
				if(pidisnull!=null&&!pidisnull.equals(""))
					paramMap.put("pidisnull", pidisnull);
				String pidisnotnull=request.getParameter("pidisnotnull");
				if(pidisnotnull!=null&&!pidisnotnull.equals(""))
					paramMap.put("pidisnotnull", pidisnotnull);
				List<Goods_category> list=iGoods_categoryService.selectGoods_categoryByParam(paramMap);
				int totalnumber=iGoods_categoryService.selectCountGoods_categoryByParam(paramMap);
				Map tempMap=new HashMap();
				resultMap.put("status", "0");
				tempMap.put("num", totalnumber);
				tempMap.put("data", list);
				resultMap.put("msg", tempMap);
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "分页参数不能为空！");
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/allGoods_category")
	@ResponseBody
	public Map all(HttpServletRequest request, HttpServletResponse response,Goods_category goods_category)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			Map paramMap=new HashMap();
			paramMap.put("orderBy","ID DESC"); 
			paramMap.put("id",goods_category.getId());
			paramMap.put("pid",goods_category.getPid());
			paramMap.put("ctg_name",goods_category.getCtg_name());
			paramMap.put("status",goods_category.getStatus());
			paramMap.put("deer_flag", goods_category.getDeer_flag());
			String cd_dtFrom=request.getParameter("cd_dtFrom");
			String cd_dtTo=request.getParameter("cd_dtTo");
			if(cd_dtFrom!=null&&!cd_dtFrom.equals(""))
			paramMap.put("cd_dtFrom", sdf.parse(cd_dtFrom));
			if(cd_dtTo!=null&&!cd_dtTo.equals(""))
			paramMap.put("cd_dtTo", sdf.parse(cd_dtTo));
			String up_dtFrom=request.getParameter("up_dtFrom");
			String up_dtTo=request.getParameter("up_dtTo");
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
			String pidisnull=request.getParameter("pidisnull");
			if(pidisnull!=null&&!pidisnull.equals(""))
				paramMap.put("pidisnull", pidisnull);
			int totalnumber=iGoods_categoryService.selectCountGoods_categoryByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			List<Goods_category> list=iGoods_categoryService.selectGoods_categoryByParam(paramMap);
			Map tempMap=new HashMap();
			resultMap.put("status", "0");
			tempMap.put("num", totalnumber);
			tempMap.put("data", list);
			resultMap.put("msg", tempMap);
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/treeGoods_category")
	@ResponseBody
	public Map tree(HttpServletRequest request, HttpServletResponse response,Goods_category goods_category)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			 
			Map paramMap=new HashMap();
			String orderBy=request.getParameter("orderBy");
			if(orderBy!=null){
				paramMap.put("orderBy",orderBy); 
			}
			else{
				paramMap.put("orderBy","ID DESC"); 
			}
			paramMap.put("seq",goods_category.getSeq());
			paramMap.put("id",goods_category.getId());
			paramMap.put("pid",goods_category.getPid());
			paramMap.put("ctg_name",goods_category.getCtg_name());
			paramMap.put("status",goods_category.getStatus());
			String cd_dtFrom=request.getParameter("cd_dtFrom");
			String cd_dtTo=request.getParameter("cd_dtTo");
			if(cd_dtFrom!=null&&!cd_dtFrom.equals(""))
			paramMap.put("cd_dtFrom", sdf.parse(cd_dtFrom));
			if(cd_dtTo!=null&&!cd_dtTo.equals(""))
			paramMap.put("cd_dtTo", sdf.parse(cd_dtTo));
			String up_dtFrom=request.getParameter("up_dtFrom");
			String up_dtTo=request.getParameter("up_dtTo");
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
			paramMap.put("pidisnull", "1");
			paramMap.put("deer_flag", goods_category.getDeer_flag());
			int totalnumber=iGoods_categoryService.selectCountGoods_categoryByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			List<Goods_category> list=iGoods_categoryService.treeGoods_categoryByParam(paramMap);
			
			
			Map tempMap=new HashMap();
			resultMap.put("status", "0");
			tempMap.put("num", totalnumber);
			tempMap.put("data", list);
			resultMap.put("msg", tempMap);
			  
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
}
