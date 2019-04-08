package com.deershop.controller.lov;
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
import com.deershop.service.lov.ILovService;
import com.deershop.model.lov.Lov;
@Controller
public class LovController {
	@Autowired
	private ILovService iLovService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addLov")
	@ResponseBody
	public Map add(Lov lov){
		Map resultMap=new HashMap();
		try {
			iLovService.addLov(lov);
			resultMap.put("status", "0");
			resultMap.put("msg", lov.getId());
			logger.info("新建成功，主键："+lov.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdLov")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Lov lov){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Lov.class);
			List<Lov> list = (List<Lov>)objectMapper.readValue(data, javaType);
			iLovService.muladdLov(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+lov.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteLov")
	@ResponseBody
	public Map delete(Lov lov){
		Map resultMap=new HashMap();
		try {
			if(lov.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iLovService.deleteLov(lov.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+lov.getId());
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
	@RequestMapping("/selectLov")
	@ResponseBody
	public Map select(Lov lov){
		Map resultMap=new HashMap();
		try {
			if(lov.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Lov resultSelect=iLovService.selectLovById(lov.getId()+"");
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
	@RequestMapping("/getCashrate")
	@ResponseBody
	public Map getCashrate(Lov lov){
		Map resultMap=new HashMap();
		try {
			
			Map paramMap=new HashMap();
			paramMap.put("fromPage",0);
			paramMap.put("toPage",1); 
			paramMap.put("lov_name","提现费率"); 
			List<Lov> list=iLovService.selectLovByParam(paramMap);
			if(list.size()>0){
				resultMap.put("status", "0");
				resultMap.put("msg", list.get(0).getImg());
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "未设置提现费率！");
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
	@RequestMapping("/setCashrate")
	@ResponseBody
	public Map setCashrate(Lov lov,HttpServletRequest request){
		Map resultMap=new HashMap();
		try {
			String cashrate=request.getParameter("cashrate");
			if(cashrate==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Map paramMap=new HashMap();
				paramMap.put("fromPage",0);
				paramMap.put("toPage",1); 
				paramMap.put("lov_name","提现费率"); 
				List<Lov> list=iLovService.selectLovByParam(paramMap);
				if(list.size()>0){
					Lov temp= new Lov();
					temp.setId(list.get(0).getId());
					temp.setImg(cashrate);
					iLovService.updateLov(temp);
					resultMap.put("status", "0");
					resultMap.put("msg", "设置成功！");
				}
				else{
					Lov temp= new Lov();
					temp.setId(list.get(0).getId());
					temp.setLov_name("提现费率");
					temp.setImg(cashrate);
					iLovService.addLov(temp);
				}
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
	@RequestMapping("/updateLov")
	@ResponseBody
	public Map update(Lov lov){
		Map resultMap=new HashMap();
		try {
			if(lov.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iLovService.updateLov(lov);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+lov.getId());
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
	@RequestMapping("/listLov")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Lov lov)
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
				paramMap.put("id",lov.getId());
				paramMap.put("lov_name",lov.getLov_name());
				paramMap.put("lov_type",lov.getLov_type());
				paramMap.put("img",lov.getImg());
				paramMap.put("status",lov.getStatus());
				String c_dtFrom=request.getParameter("c_dtFrom");
				String c_dtTo=request.getParameter("c_dtTo");
				if(c_dtFrom!=null&&!c_dtFrom.equals(""))
				paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
				if(c_dtTo!=null&&!c_dtTo.equals(""))
				paramMap.put("c_dtTo", sdf.parse(c_dtTo));
				String up_dtFrom=request.getParameter("up_dtFrom");
				String up_dtTo=request.getParameter("up_dtTo");
				if(up_dtFrom!=null&&!up_dtFrom.equals(""))
				paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
				if(up_dtTo!=null&&!up_dtTo.equals(""))
				paramMap.put("up_dtTo", sdf.parse(up_dtTo));
				List<Lov> list=iLovService.selectLovByParam(paramMap);
				int totalnumber=iLovService.selectCountLovByParam(paramMap);
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
}
