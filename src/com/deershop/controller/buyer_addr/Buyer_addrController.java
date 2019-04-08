package com.deershop.controller.buyer_addr;
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
import com.deershop.service.buyer_addr.IBuyer_addrService;
import com.deershop.model.buyer_addr.Buyer_addr;
@Controller
public class Buyer_addrController {
	@Autowired
	private IBuyer_addrService iBuyer_addrService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addBuyer_addr")
	@ResponseBody
	public Map add(Buyer_addr buyer_addr){
		Map resultMap=new HashMap();
		try {
			iBuyer_addrService.addBuyer_addr(buyer_addr);
			resultMap.put("status", "0");
			resultMap.put("msg", buyer_addr.getId());
			logger.info("新建成功，主键："+buyer_addr.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdBuyer_addr")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Buyer_addr buyer_addr){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Buyer_addr.class);
			List<Buyer_addr> list = (List<Buyer_addr>)objectMapper.readValue(data, javaType);
			iBuyer_addrService.muladdBuyer_addr(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+buyer_addr.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteBuyer_addr")
	@ResponseBody
	public Map delete(Buyer_addr buyer_addr){
		Map resultMap=new HashMap();
		try {
			if(buyer_addr.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iBuyer_addrService.deleteBuyer_addr(buyer_addr.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+buyer_addr.getId());
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
	@RequestMapping("/selectBuyer_addr")
	@ResponseBody
	public Map select(Buyer_addr buyer_addr){
		Map resultMap=new HashMap();
		try {
			if(buyer_addr.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Buyer_addr resultSelect=iBuyer_addrService.selectBuyer_addrById(buyer_addr.getId()+"");
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
	@RequestMapping("/updateBuyer_addr")
	@ResponseBody
	public Map update(Buyer_addr buyer_addr){
		Map resultMap=new HashMap();
		try {
			if(buyer_addr.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iBuyer_addrService.updateBuyer_addr(buyer_addr);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+buyer_addr.getId());
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
	@RequestMapping("/listBuyer_addr")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Buyer_addr buyer_addr)
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
				paramMap.put("id",buyer_addr.getId());
				paramMap.put("c_id",buyer_addr.getC_id());
				paramMap.put("buyer_id",buyer_addr.getBuyer_id());
				paramMap.put("sheng_id",buyer_addr.getSheng_id());
				paramMap.put("shi_id",buyer_addr.getShi_id());
				paramMap.put("qu_id",buyer_addr.getQu_id());
				paramMap.put("addr",buyer_addr.getAddr());
				paramMap.put("receiver",buyer_addr.getReceiver());
				paramMap.put("receiver_tel",buyer_addr.getReceiver_tel());
				paramMap.put("is_main_addr",buyer_addr.getIs_main_addr());
				paramMap.put("status",buyer_addr.getStatus());
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
				List<Buyer_addr> list=iBuyer_addrService.selectBuyer_addrByParam(paramMap);
				int totalnumber=iBuyer_addrService.selectCountBuyer_addrByParam(paramMap);
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
