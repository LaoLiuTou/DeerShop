package com.deershop.controller.postage;
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
import com.deershop.service.postage.IPostageService;
import com.deershop.model.postage.Postage;
@Controller
public class PostageController {
	@Autowired
	private IPostageService iPostageService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addPostage")
	@ResponseBody
	public Map add(Postage postage){
		Map resultMap=new HashMap();
		try {
			iPostageService.addPostage(postage);
			resultMap.put("status", "0");
			resultMap.put("msg", postage.getId());
			logger.info("新建成功，主键："+postage.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdPostage")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Postage postage){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Postage.class);
			List<Postage> list = (List<Postage>)objectMapper.readValue(data, javaType);
			iPostageService.muladdPostage(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+postage.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deletePostage")
	@ResponseBody
	public Map delete(Postage postage){
		Map resultMap=new HashMap();
		try {
			if(postage.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iPostageService.deletePostage(postage.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+postage.getId());
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
	@RequestMapping("/selectPostage")
	@ResponseBody
	public Map select(Postage postage){
		Map resultMap=new HashMap();
		try {
			if(postage.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Postage resultSelect=iPostageService.selectPostageById(postage.getId()+"");
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
	@RequestMapping("/updatePostage")
	@ResponseBody
	public Map update(Postage postage){
		Map resultMap=new HashMap();
		try {
			if(postage.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iPostageService.updatePostage(postage);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+postage.getId());
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
	@RequestMapping("/listPostage")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Postage postage)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String page=request.getParameter("page");
			String size=request.getParameter("size");
			if(page!=null&&size!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",(Integer.parseInt(page)-1)*Integer.parseInt(size));
				paramMap.put("toPage",Integer.parseInt(size)); 
				paramMap.put("orderBy","ID DESC"); 
				paramMap.put("id",postage.getId());
				paramMap.put("seller_id",postage.getSeller_id());
				paramMap.put("postage_name",postage.getPostage_name());
				paramMap.put("postage_desc",postage.getPostage_desc());
				paramMap.put("rule",postage.getRule());
				paramMap.put("is_use",postage.getIs_use());
				paramMap.put("status",postage.getStatus());
				paramMap.put("postagecol",postage.getPostagecol());
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
				List<Postage> list=iPostageService.selectPostageByParam(paramMap);
				int totalnumber=iPostageService.selectCountPostageByParam(paramMap);
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
	@RequestMapping("/allPostage")
	@ResponseBody
	public Map all(HttpServletRequest request, HttpServletResponse response,Postage postage)
			throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			Map paramMap=new HashMap();
			paramMap.put("orderBy","ID DESC"); 
			paramMap.put("id",postage.getId());
			paramMap.put("seller_id",postage.getSeller_id());
			paramMap.put("postage_name",postage.getPostage_name());
			paramMap.put("postage_desc",postage.getPostage_desc());
			paramMap.put("rule",postage.getRule());
			paramMap.put("is_use",postage.getIs_use());
			paramMap.put("status",postage.getStatus());
			paramMap.put("postagecol",postage.getPostagecol());
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
			int totalnumber=iPostageService.selectCountPostageByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			List<Postage> list=iPostageService.selectPostageByParam(paramMap);
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
