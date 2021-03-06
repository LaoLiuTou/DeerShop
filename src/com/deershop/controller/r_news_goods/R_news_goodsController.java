package com.deershop.controller.r_news_goods;
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
import com.deershop.service.r_news_goods.IR_news_goodsService;
import com.deershop.model.r_news_goods.R_news_goods;
@Controller
public class R_news_goodsController {
	@Autowired
	private IR_news_goodsService iR_news_goodsService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addR_news_goods")
	@ResponseBody
	public Map add(R_news_goods r_news_goods){
		Map resultMap=new HashMap();
		try {
			iR_news_goodsService.addR_news_goods(r_news_goods);
			resultMap.put("status", "0");
			resultMap.put("msg", r_news_goods.getId());
			logger.info("新建成功，主键："+r_news_goods.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdR_news_goods")
	@ResponseBody
	public Map muladd(HttpServletRequest request,R_news_goods r_news_goods){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, R_news_goods.class);
			List<R_news_goods> list = (List<R_news_goods>)objectMapper.readValue(data, javaType);
			iR_news_goodsService.muladdR_news_goods(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+r_news_goods.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteR_news_goods")
	@ResponseBody
	public Map delete(R_news_goods r_news_goods){
		Map resultMap=new HashMap();
		try {
			if(r_news_goods.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iR_news_goodsService.deleteR_news_goods(r_news_goods.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+r_news_goods.getId());
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
	@RequestMapping("/selectR_news_goods")
	@ResponseBody
	public Map select(R_news_goods r_news_goods){
		Map resultMap=new HashMap();
		try {
			if(r_news_goods.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				R_news_goods resultSelect=iR_news_goodsService.selectR_news_goodsById(r_news_goods.getId()+"");
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
	@RequestMapping("/updateR_news_goods")
	@ResponseBody
	public Map update(R_news_goods r_news_goods){
		Map resultMap=new HashMap();
		try {
			if(r_news_goods.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iR_news_goodsService.updateR_news_goods(r_news_goods);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+r_news_goods.getId());
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
	@RequestMapping("/listR_news_goods")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,R_news_goods r_news_goods)
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
				paramMap.put("id",r_news_goods.getId());
				paramMap.put("c_id",r_news_goods.getC_id());
				paramMap.put("news_id",r_news_goods.getNews_id());
				paramMap.put("goods_id",r_news_goods.getGoods_id());
				paramMap.put("url",r_news_goods.getUrl());
				paramMap.put("status",r_news_goods.getStatus());
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
				List<R_news_goods> list=iR_news_goodsService.selectR_news_goodsByParam(paramMap);
				int totalnumber=iR_news_goodsService.selectCountR_news_goodsByParam(paramMap);
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
