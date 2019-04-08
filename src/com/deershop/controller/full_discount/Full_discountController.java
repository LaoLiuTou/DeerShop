package com.deershop.controller.full_discount;
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
import com.deershop.service.full_discount.IFull_discountService;
import com.deershop.model.full_discount.Full_discount;
@Controller
public class Full_discountController {
	@Autowired
	private IFull_discountService iFull_discountService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addFull_discount")
	@ResponseBody
	public Map add(Full_discount full_discount){
		Map resultMap=new HashMap();
		try {
			iFull_discountService.addFull_discount(full_discount);
			resultMap.put("status", "0");
			resultMap.put("msg", full_discount.getId());
			logger.info("新建成功，主键："+full_discount.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdFull_discount")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Full_discount full_discount){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Full_discount.class);
			List<Full_discount> list = (List<Full_discount>)objectMapper.readValue(data, javaType);
			iFull_discountService.muladdFull_discount(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+full_discount.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteFull_discount")
	@ResponseBody
	public Map delete(Full_discount full_discount){
		Map resultMap=new HashMap();
		try {
			if(full_discount.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iFull_discountService.deleteFull_discount(full_discount.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+full_discount.getId());
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
	@RequestMapping("/selectFull_discount")
	@ResponseBody
	public Map select(Full_discount full_discount){
		Map resultMap=new HashMap();
		try {
			if(full_discount.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Full_discount resultSelect=iFull_discountService.selectFull_discountById(full_discount.getId()+"");
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
	@RequestMapping("/updateFull_discount")
	@ResponseBody
	public Map update(Full_discount full_discount){
		Map resultMap=new HashMap();
		try {
			if(full_discount.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iFull_discountService.updateFull_discount(full_discount);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+full_discount.getId());
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
	@RequestMapping("/listFull_discount")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Full_discount full_discount)
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
				paramMap.put("id",full_discount.getId());
				paramMap.put("seller_id",full_discount.getSeller_id());
				paramMap.put("full_type",full_discount.getFull_type());
				paramMap.put("full_name",full_discount.getFull_name());
				paramMap.put("rule",full_discount.getRule());
				paramMap.put("full_desc",full_discount.getFull_desc());
				paramMap.put("is_all",full_discount.getIs_all());
				String create_dtFrom=request.getParameter("create_dtFrom");
				String create_dtTo=request.getParameter("create_dtTo");
				if(create_dtFrom!=null&&!create_dtFrom.equals(""))
				paramMap.put("create_dtFrom", sdf.parse(create_dtFrom));
				if(create_dtTo!=null&&!create_dtTo.equals(""))
				paramMap.put("create_dtTo", sdf.parse(create_dtTo));
				String st_dtFrom=request.getParameter("st_dtFrom");
				String st_dtTo=request.getParameter("st_dtTo");
				if(st_dtFrom!=null&&!st_dtFrom.equals(""))
				paramMap.put("st_dtFrom", sdf.parse(st_dtFrom));
				if(st_dtTo!=null&&!st_dtTo.equals(""))
				paramMap.put("st_dtTo", sdf.parse(st_dtTo));
				String ed_dtFrom=request.getParameter("ed_dtFrom");
				String ed_dtTo=request.getParameter("ed_dtTo");
				if(ed_dtFrom!=null&&!ed_dtFrom.equals(""))
				paramMap.put("ed_dtFrom", sdf.parse(ed_dtFrom));
				if(ed_dtTo!=null&&!ed_dtTo.equals(""))
				paramMap.put("ed_dtTo", sdf.parse(ed_dtTo));
				String stop_dtFrom=request.getParameter("stop_dtFrom");
				String stop_dtTo=request.getParameter("stop_dtTo");
				if(stop_dtFrom!=null&&!stop_dtFrom.equals(""))
				paramMap.put("stop_dtFrom", sdf.parse(stop_dtFrom));
				if(stop_dtTo!=null&&!stop_dtTo.equals(""))
				paramMap.put("stop_dtTo", sdf.parse(stop_dtTo));
				paramMap.put("publish_status",full_discount.getPublish_status());
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
				List<Full_discount> list=iFull_discountService.selectFull_discountByParam(paramMap);
				int totalnumber=iFull_discountService.selectCountFull_discountByParam(paramMap);
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
