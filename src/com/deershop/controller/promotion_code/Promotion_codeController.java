package com.deershop.controller.promotion_code;
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
import com.deershop.service.promotion_code.IPromotion_codeService;
import com.deershop.model.promotion_code.Promotion_code;
@Controller
public class Promotion_codeController {
	@Autowired
	private IPromotion_codeService iPromotion_codeService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addPromotion_code")
	@ResponseBody
	public Map add(Promotion_code promotion_code){
		Map resultMap=new HashMap();
		try {
			iPromotion_codeService.addPromotion_code(promotion_code);
			resultMap.put("status", "0");
			resultMap.put("msg", promotion_code.getId());
			logger.info("新建成功，主键："+promotion_code.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdPromotion_code")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Promotion_code promotion_code){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Promotion_code.class);
			List<Promotion_code> list = (List<Promotion_code>)objectMapper.readValue(data, javaType);
			iPromotion_codeService.muladdPromotion_code(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+promotion_code.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deletePromotion_code")
	@ResponseBody
	public Map delete(Promotion_code promotion_code){
		Map resultMap=new HashMap();
		try {
			if(promotion_code.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iPromotion_codeService.deletePromotion_code(promotion_code.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+promotion_code.getId());
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
	@RequestMapping("/selectPromotion_code")
	@ResponseBody
	public Map select(Promotion_code promotion_code){
		Map resultMap=new HashMap();
		try {
			if(promotion_code.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Promotion_code resultSelect=iPromotion_codeService.selectPromotion_codeById(promotion_code.getId()+"");
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
	@RequestMapping("/updatePromotion_code")
	@ResponseBody
	public Map update(Promotion_code promotion_code){
		Map resultMap=new HashMap();
		try {
			if(promotion_code.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iPromotion_codeService.updatePromotion_code(promotion_code);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+promotion_code.getId());
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
	@RequestMapping("/listPromotion_code")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Promotion_code promotion_code)
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
				paramMap.put("id",promotion_code.getId());
				paramMap.put("seller_id",promotion_code.getSeller_id());
				paramMap.put("promotion_type",promotion_code.getPromotion_type());
				paramMap.put("promotion_name",promotion_code.getPromotion_name());
				paramMap.put("rule",promotion_code.getRule());
				paramMap.put("promotion_desc",promotion_code.getPromotion_desc());
				paramMap.put("publish_amount",promotion_code.getPublish_amount());
				paramMap.put("now_amount",promotion_code.getNow_amount());
				paramMap.put("limit_amount",promotion_code.getLimit_amount());
				paramMap.put("is_all",promotion_code.getIs_all());
				String create_dtFrom=request.getParameter("create_dtFrom");
				String create_dtTo=request.getParameter("create_dtTo");
				if(create_dtFrom!=null&&!create_dtFrom.equals(""))
				paramMap.put("create_dtFrom", sdf.parse(create_dtFrom));
				if(create_dtTo!=null&&!create_dtTo.equals(""))
				paramMap.put("create_dtTo", sdf.parse(create_dtTo));
				String publish_dtFrom=request.getParameter("publish_dtFrom");
				String publish_dtTo=request.getParameter("publish_dtTo");
				if(publish_dtFrom!=null&&!publish_dtFrom.equals(""))
				paramMap.put("publish_dtFrom", sdf.parse(publish_dtFrom));
				if(publish_dtTo!=null&&!publish_dtTo.equals(""))
				paramMap.put("publish_dtTo", sdf.parse(publish_dtTo));
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
				String cancel_dtFrom=request.getParameter("cancel_dtFrom");
				String cancel_dtTo=request.getParameter("cancel_dtTo");
				if(cancel_dtFrom!=null&&!cancel_dtFrom.equals(""))
				paramMap.put("cancel_dtFrom", sdf.parse(cancel_dtFrom));
				if(cancel_dtTo!=null&&!cancel_dtTo.equals(""))
				paramMap.put("cancel_dtTo", sdf.parse(cancel_dtTo));
				String stop_dtFrom=request.getParameter("stop_dtFrom");
				String stop_dtTo=request.getParameter("stop_dtTo");
				if(stop_dtFrom!=null&&!stop_dtFrom.equals(""))
				paramMap.put("stop_dtFrom", sdf.parse(stop_dtFrom));
				if(stop_dtTo!=null&&!stop_dtTo.equals(""))
				paramMap.put("stop_dtTo", sdf.parse(stop_dtTo));
				paramMap.put("status",promotion_code.getStatus());
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
				List<Promotion_code> list=iPromotion_codeService.selectPromotion_codeByParam(paramMap);
				int totalnumber=iPromotion_codeService.selectCountPromotion_codeByParam(paramMap);
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
