package com.deershop.controller.news;
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.deershop.service.news.INewsService;
import com.deershop.service.r_news_goods.IR_news_goodsService;
import com.deershop.model.news.News;
import com.deershop.model.r_news_goods.R_news_goods;
@Controller
public class NewsController {
	@Autowired
	private INewsService iNewsService;
	@Autowired
	private IR_news_goodsService iR_news_goodsService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addNews")
	@ResponseBody
	public Map add(HttpServletRequest request,News news){
		Map resultMap=new HashMap();
		try {
			Map paramMap=new HashMap();
			paramMap.put("title",news.getTitle());
			int totalnumber=iNewsService.selectCountNewsByParam(paramMap);
			if(totalnumber>0){
				resultMap.put("status", "-1");
				resultMap.put("msg", "该资讯标题已存在！");
			}
			else{
				String add_goods=request.getParameter("add_goods");
				iNewsService.addNews(news,add_goods);
				resultMap.put("status", "0");
				resultMap.put("msg", news.getId());
				logger.info("新建成功，主键："+news.getId());
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
	@RequestMapping("/muladdNews")
	@ResponseBody
	public Map muladd(HttpServletRequest request,News news){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, News.class);
			List<News> list = (List<News>)objectMapper.readValue(data, javaType);
			iNewsService.muladdNews(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+news.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteNews")
	@ResponseBody
	public Map delete(News news){
		Map resultMap=new HashMap();
		try {
			if(news.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iNewsService.deleteNews(news.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+news.getId());
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
	@RequestMapping("/selectNews")
	@ResponseBody
	public Map select(News news){
		Map resultMap=new HashMap();
		try {
			if(news.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				News resultSelect=iNewsService.selectNewsById(news.getId()+"");
				Map paramMap=new HashMap();
				paramMap.put("news_id",news.getId()+"");
				int count=iR_news_goodsService.selectCountR_news_goodsByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",count); 
				List<R_news_goods> list=iR_news_goodsService.selectR_news_goodsByParam(paramMap);
				List<String> ids=new ArrayList<String>();
				for(R_news_goods r:list){
					ids.add(r.getGoods_id()+"");
				}
				resultSelect.setGoogs_ids("["+StringUtils.join(ids,",")+"]");
				
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
	@RequestMapping("/updateNews")
	@ResponseBody
	public Map update(HttpServletRequest request,News news){
		Map resultMap=new HashMap();
		try {
			if(news.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				String add_goods=request.getParameter("add_goods");
				int resultUpdate=iNewsService.updateNews(news,add_goods);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+news.getId());
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
	@RequestMapping("/listNews")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,News news)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String page=request.getParameter("page");
			String size=request.getParameter("size");
			if(page!=null&&size!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",(Integer.parseInt(page)-1)*Integer.parseInt(size));
				paramMap.put("toPage",Integer.parseInt(size)); 
				String orderBy=request.getParameter("orderBy");
				if(orderBy!=null){
					paramMap.put("orderBy",orderBy); 
				}
				else{
					paramMap.put("orderBy","ID DESC"); 
				}
				
				paramMap.put("id",news.getId());
				paramMap.put("c_id",news.getC_id());
				paramMap.put("title",news.getTitle());
				paramMap.put("img",news.getImg());
				paramMap.put("comments",news.getComments());
				paramMap.put("sort",news.getSort());
				String publish_dtFrom=request.getParameter("publish_dtFrom");
				String publish_dtTo=request.getParameter("publish_dtTo");
				if(publish_dtFrom!=null&&!publish_dtFrom.equals(""))
				paramMap.put("publish_dtFrom", sdf.parse(publish_dtFrom));
				if(publish_dtTo!=null&&!publish_dtTo.equals(""))
				paramMap.put("publish_dtTo", sdf.parse(publish_dtTo));
				paramMap.put("status",news.getStatus());
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
				List<News> list=iNewsService.selectNewsByParam(paramMap);
				int totalnumber=iNewsService.selectCountNewsByParam(paramMap);
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
