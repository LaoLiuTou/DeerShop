package com.deershop.controller.cash_apply;
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
import com.deershop.service.cash_apply.ICash_applyService;
import com.deershop.model.cash_apply.Cash_apply;
@Controller
public class Cash_applyController {
	@Autowired
	private ICash_applyService iCash_applyService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addCash_apply")
	@ResponseBody
	public Map add(Cash_apply cash_apply){
		Map resultMap=new HashMap();
		try {
			if(cash_apply.getCash_rate()==null||cash_apply.getPrice()==null||
					cash_apply.getCash_service()==null||cash_apply.getCash_price()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int result=iCash_applyService.addCash_apply(cash_apply);
				if(result>0){
					resultMap.put("status", "0");
					resultMap.put("msg", cash_apply.getId());
					logger.info("新建成功，主键："+cash_apply.getId());
				}
				else if(result==-1){
					resultMap.put("status", "-1");
					resultMap.put("msg", "金额计算错误！");
				}
				else if(result==-2){
					resultMap.put("status", "-1");
					resultMap.put("msg", "提现金额不能为小于一元！");
				}
				else if(result==-3){
					resultMap.put("status", "-1");
					resultMap.put("msg", "可提现金额不足！");
				}
				else {
					resultMap.put("status", "-1");
					resultMap.put("msg", "新建失败！");
				}
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
	@RequestMapping("/muladdCash_apply")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Cash_apply cash_apply){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Cash_apply.class);
			List<Cash_apply> list = (List<Cash_apply>)objectMapper.readValue(data, javaType);
			iCash_applyService.muladdCash_apply(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+cash_apply.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteCash_apply")
	@ResponseBody
	public Map delete(Cash_apply cash_apply){
		Map resultMap=new HashMap();
		try {
			if(cash_apply.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iCash_applyService.deleteCash_apply(cash_apply.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+cash_apply.getId());
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
	@RequestMapping("/selectCash_apply")
	@ResponseBody
	public Map select(Cash_apply cash_apply){
		Map resultMap=new HashMap();
		try {
			if(cash_apply.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Cash_apply resultSelect=iCash_applyService.selectCash_applyById(cash_apply.getId()+"");
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
	@RequestMapping("/updateCash_apply")
	@ResponseBody
	public Map update(Cash_apply cash_apply){
		Map resultMap=new HashMap();
		try {
			if(cash_apply.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iCash_applyService.updateCash_apply(cash_apply);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+cash_apply.getId());
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
	@RequestMapping("/offlinePay")
	@ResponseBody
	public Map offlinePay (Cash_apply cash_apply){
		Map resultMap=new HashMap();
		try {
			if(cash_apply.getId()==null||cash_apply.getCash_type()==null||
					cash_apply.getCash_img()==null||cash_apply.getCash_deal_id()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iCash_applyService.offlinePay(cash_apply);
				resultMap.put("status", "0");
				resultMap.put("msg", "提现成功！");
				logger.info("提现成功，主键："+cash_apply.getId());
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "提现失败！");
			logger.info("提现失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/payToCard")
	@ResponseBody
	public Map payToCard (Cash_apply cash_apply){
		Map resultMap=new HashMap();
		try {
			if(cash_apply.getId()==null||cash_apply.getCash_deal_id()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				String resultUpdate=iCash_applyService.payToCard(cash_apply);
				if(resultUpdate.equals("提现成功！")){
					resultMap.put("status", "0");
				}
				else{
					resultMap.put("status", "-1");
				}
				
				resultMap.put("msg", resultUpdate);
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "提现失败！");
			logger.info("提现失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/payToWechat")
	@ResponseBody
	public Map payToWechat (Cash_apply cash_apply){
		Map resultMap=new HashMap();
		try {
			if(cash_apply.getId()==null||cash_apply.getCash_deal_id()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				String resultUpdate=iCash_applyService.payToWechat(cash_apply);
				if(resultUpdate.equals("提现成功！")){
					resultMap.put("status", "0");
				}
				else{
					resultMap.put("status", "-1");
				}
				
				resultMap.put("msg", resultUpdate);
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "提现失败！");
			logger.info("提现失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listCash_apply")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Cash_apply cash_apply)
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
				paramMap.put("id",cash_apply.getId());
				paramMap.put("seller_id",cash_apply.getSeller_id());
				paramMap.put("price",cash_apply.getPrice());
				paramMap.put("cash_type",cash_apply.getCash_type());
				paramMap.put("cash_info",cash_apply.getCash_info());
				paramMap.put("cash_status",cash_apply.getCash_status());
				paramMap.put("cash_price",cash_apply.getCash_price());
				paramMap.put("cash_service",cash_apply.getCash_service());
				paramMap.put("cash_rate",cash_apply.getCash_rate());
				paramMap.put("cash_code",cash_apply.getCash_code());
				paramMap.put("cash_deal_id",cash_apply.getCash_deal_id());
				String cash_st_dtFrom=request.getParameter("cash_st_dtFrom");
				String cash_st_dtTo=request.getParameter("cash_st_dtTo");
				if(cash_st_dtFrom!=null&&!cash_st_dtFrom.equals(""))
				paramMap.put("cash_st_dtFrom", sdf.parse(cash_st_dtFrom));
				if(cash_st_dtTo!=null&&!cash_st_dtTo.equals(""))
				paramMap.put("cash_st_dtTo", sdf.parse(cash_st_dtTo));
				String cash_ok_dtFrom=request.getParameter("cash_ok_dtFrom");
				String cash_ok_dtTo=request.getParameter("cash_ok_dtTo");
				if(cash_ok_dtFrom!=null&&!cash_ok_dtFrom.equals(""))
				paramMap.put("cash_ok_dtFrom", sdf.parse(cash_ok_dtFrom));
				if(cash_ok_dtTo!=null&&!cash_ok_dtTo.equals(""))
				paramMap.put("cash_ok_dtTo", sdf.parse(cash_ok_dtTo));
				String deal_dateFrom=request.getParameter("deal_dateFrom");
				String deal_dateTo=request.getParameter("deal_dateTo");
				if(deal_dateFrom!=null&&!deal_dateFrom.equals(""))
				paramMap.put("deal_dateFrom", sdf.parse(deal_dateFrom));
				if(deal_dateTo!=null&&!deal_dateTo.equals(""))
				paramMap.put("deal_dateTo", sdf.parse(deal_dateTo));
				
				//search
				String search=request.getParameter("search");
				paramMap.put("search", search);
				
				List<Cash_apply> list=iCash_applyService.selectCash_applyByParam(paramMap);
				int totalnumber=iCash_applyService.selectCountCash_applyByParam(paramMap);
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
