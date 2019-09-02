package com.deershop.controller.buyer;
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
import com.deershop.service.buyer.IBuyerService;
import com.deershop.model.buyer.Buyer;
@Controller
public class BuyerController {
	@Autowired
	private IBuyerService iBuyerService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addBuyer")
	@ResponseBody
	public Map add(Buyer buyer){
		Map resultMap=new HashMap();
		try {
			iBuyerService.addBuyer(buyer);
			resultMap.put("status", "0");
			resultMap.put("msg", buyer.getId());
			logger.info("新建成功，主键："+buyer.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdBuyer")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Buyer buyer){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Buyer.class);
			List<Buyer> list = (List<Buyer>)objectMapper.readValue(data, javaType);
			iBuyerService.muladdBuyer(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+buyer.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteBuyer")
	@ResponseBody
	public Map delete(Buyer buyer){
		Map resultMap=new HashMap();
		try {
			if(buyer.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iBuyerService.deleteBuyer(buyer.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+buyer.getId());
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
	@RequestMapping("/selectBuyer")
	@ResponseBody
	public Map select(Buyer buyer){
		Map resultMap=new HashMap();
		try {
			if(buyer.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Buyer resultSelect=iBuyerService.selectBuyerById(buyer.getId()+"");
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
	@RequestMapping("/updateBuyer")
	@ResponseBody
	public Map update(Buyer buyer){
		Map resultMap=new HashMap();
		try {
			if(buyer.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iBuyerService.updateBuyer(buyer);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+buyer.getId());
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
	@RequestMapping("/listBuyer")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Buyer buyer)
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
				paramMap.put("id",buyer.getId());
				paramMap.put("c_id",buyer.getC_id());
				paramMap.put("main_addr_id",buyer.getMain_addr_id());
				paramMap.put("wechat",buyer.getWechat());
				paramMap.put("point",buyer.getPoint());
				paramMap.put("img",buyer.getImg());
				String last_login_dtFrom=request.getParameter("last_login_dtFrom");
				String last_login_dtTo=request.getParameter("last_login_dtTo");
				if(last_login_dtFrom!=null&&!last_login_dtFrom.equals(""))
				paramMap.put("last_login_dtFrom", sdf.parse(last_login_dtFrom));
				if(last_login_dtTo!=null&&!last_login_dtTo.equals(""))
				paramMap.put("last_login_dtTo", sdf.parse(last_login_dtTo));
				paramMap.put("status",buyer.getStatus());
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
				
				paramMap.put("sex",buyer.getSex());
				paramMap.put("id_code",buyer.getId_code());
				
				List<Buyer> list=iBuyerService.selectBuyerByParam(paramMap);
				int totalnumber=iBuyerService.selectCountBuyerByParam(paramMap);
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
