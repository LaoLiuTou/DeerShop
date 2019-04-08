package com.deershop.controller.discount;
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
import com.deershop.service.discount.IDiscountService;
import com.deershop.model.discount.Discount;
@Controller
public class DiscountController {
	@Autowired
	private IDiscountService iDiscountService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addDiscount")
	@ResponseBody
	public Map add(Discount discount){
		Map resultMap=new HashMap();
		try {
			iDiscountService.addDiscount(discount);
			resultMap.put("status", "0");
			resultMap.put("msg", discount.getId());
			logger.info("新建成功，主键："+discount.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdDiscount")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Discount discount){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Discount.class);
			List<Discount> list = (List<Discount>)objectMapper.readValue(data, javaType);
			iDiscountService.muladdDiscount(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+discount.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteDiscount")
	@ResponseBody
	public Map delete(Discount discount){
		Map resultMap=new HashMap();
		try {
			if(discount.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iDiscountService.deleteDiscount(discount.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+discount.getId());
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
	@RequestMapping("/selectDiscount")
	@ResponseBody
	public Map select(Discount discount){
		Map resultMap=new HashMap();
		try {
			if(discount.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Discount resultSelect=iDiscountService.selectDiscountById(discount.getId()+"");
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
	@RequestMapping("/updateDiscount")
	@ResponseBody
	public Map update(Discount discount){
		Map resultMap=new HashMap();
		try {
			if(discount.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iDiscountService.updateDiscount(discount);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+discount.getId());
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
	@RequestMapping("/listDiscount")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Discount discount)
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
				paramMap.put("id",discount.getId());
				paramMap.put("seller_id",discount.getSeller_id());
				paramMap.put("discount_name",discount.getDiscount_name());
				paramMap.put("discount_desc",discount.getDiscount_desc());
				paramMap.put("rule",discount.getRule());
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
				paramMap.put("status",discount.getStatus());
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
				List<Discount> list=iDiscountService.selectDiscountByParam(paramMap);
				int totalnumber=iDiscountService.selectCountDiscountByParam(paramMap);
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
