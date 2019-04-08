package com.deershop.controller.r_buyer_promotion;
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
import com.deershop.service.r_buyer_promotion.IR_buyer_promotionService;
import com.deershop.model.r_buyer_promotion.R_buyer_promotion;
@Controller
public class R_buyer_promotionController {
	@Autowired
	private IR_buyer_promotionService iR_buyer_promotionService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addR_buyer_promotion")
	@ResponseBody
	public Map add(R_buyer_promotion r_buyer_promotion){
		Map resultMap=new HashMap();
		try {
			iR_buyer_promotionService.addR_buyer_promotion(r_buyer_promotion);
			resultMap.put("status", "0");
			resultMap.put("msg", r_buyer_promotion.getId());
			logger.info("新建成功，主键："+r_buyer_promotion.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdR_buyer_promotion")
	@ResponseBody
	public Map muladd(HttpServletRequest request,R_buyer_promotion r_buyer_promotion){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, R_buyer_promotion.class);
			List<R_buyer_promotion> list = (List<R_buyer_promotion>)objectMapper.readValue(data, javaType);
			iR_buyer_promotionService.muladdR_buyer_promotion(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+r_buyer_promotion.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteR_buyer_promotion")
	@ResponseBody
	public Map delete(R_buyer_promotion r_buyer_promotion){
		Map resultMap=new HashMap();
		try {
			if(r_buyer_promotion.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iR_buyer_promotionService.deleteR_buyer_promotion(r_buyer_promotion.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+r_buyer_promotion.getId());
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
	@RequestMapping("/selectR_buyer_promotion")
	@ResponseBody
	public Map select(R_buyer_promotion r_buyer_promotion){
		Map resultMap=new HashMap();
		try {
			if(r_buyer_promotion.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				R_buyer_promotion resultSelect=iR_buyer_promotionService.selectR_buyer_promotionById(r_buyer_promotion.getId()+"");
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
	@RequestMapping("/updateR_buyer_promotion")
	@ResponseBody
	public Map update(R_buyer_promotion r_buyer_promotion){
		Map resultMap=new HashMap();
		try {
			if(r_buyer_promotion.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iR_buyer_promotionService.updateR_buyer_promotion(r_buyer_promotion);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+r_buyer_promotion.getId());
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
	@RequestMapping("/listR_buyer_promotion")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,R_buyer_promotion r_buyer_promotion)
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
				paramMap.put("id",r_buyer_promotion.getId());
				paramMap.put("buyer_id",r_buyer_promotion.getBuyer_id());
				paramMap.put("promotion_id",r_buyer_promotion.getPromotion_id());
				paramMap.put("receive_type",r_buyer_promotion.getReceive_type());
				String receive_dtFrom=request.getParameter("receive_dtFrom");
				String receive_dtTo=request.getParameter("receive_dtTo");
				if(receive_dtFrom!=null&&!receive_dtFrom.equals(""))
				paramMap.put("receive_dtFrom", sdf.parse(receive_dtFrom));
				if(receive_dtTo!=null&&!receive_dtTo.equals(""))
				paramMap.put("receive_dtTo", sdf.parse(receive_dtTo));
				String use_dtFrom=request.getParameter("use_dtFrom");
				String use_dtTo=request.getParameter("use_dtTo");
				if(use_dtFrom!=null&&!use_dtFrom.equals(""))
				paramMap.put("use_dtFrom", sdf.parse(use_dtFrom));
				if(use_dtTo!=null&&!use_dtTo.equals(""))
				paramMap.put("use_dtTo", sdf.parse(use_dtTo));
				paramMap.put("use_status",r_buyer_promotion.getUse_status());
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
				List<R_buyer_promotion> list=iR_buyer_promotionService.selectR_buyer_promotionByParam(paramMap);
				int totalnumber=iR_buyer_promotionService.selectCountR_buyer_promotionByParam(paramMap);
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
