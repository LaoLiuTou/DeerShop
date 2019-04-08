package com.deershop.controller.goods;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.deershop.service.goods.IGoodsService;
import com.deershop.model.goods.Goods;
@Controller
public class GoodsController {
	@Autowired
	private IGoodsService iGoodsService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addGoods")
	@ResponseBody
	public Map add(Goods goods){
		Map resultMap=new HashMap();
		try {
			iGoodsService.addGoods(goods);
			resultMap.put("status", "0");
			resultMap.put("msg", goods.getId());
			logger.info("新建成功，主键："+goods.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdGoods")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Goods goods){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Goods.class);
			List<Goods> list = (List<Goods>)objectMapper.readValue(data, javaType);
			iGoodsService.muladdGoods(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+goods.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/mulUpdateGoods")
	@ResponseBody
	public Map mulUpdate(HttpServletRequest request){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			String isHot=request.getParameter("ishot");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
			List<String> list = (List<String>)objectMapper.readValue(data, javaType);
			iGoodsService.mulupdateGoods(list,isHot);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+data);
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteGoods")
	@ResponseBody
	public Map delete(Goods goods){
		Map resultMap=new HashMap();
		try {
			if(goods.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iGoodsService.deleteGoods(goods.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+goods.getId());
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
	@RequestMapping("/selectGoods")
	@ResponseBody
	public Map select(Goods goods){
		Map resultMap=new HashMap();
		try {
			if(goods.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Goods resultSelect=iGoodsService.selectGoodsById(goods.getId()+"");
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
	@RequestMapping("/updateGoods")
	@ResponseBody
	public Map update(Goods goods){
		Map resultMap=new HashMap();
		try {
			if(goods.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iGoodsService.updateGoods(goods);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+goods.getId());
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
	@RequestMapping("/listGoods")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Goods goods)
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
				paramMap.put("id",goods.getId());
				paramMap.put("c_id",goods.getC_id());
				paramMap.put("seller_id",goods.getSeller_id());
				paramMap.put("ctg_id",goods.getCtg_id());
				paramMap.put("postage_id",goods.getPostage_id());
				paramMap.put("delivery_rang",goods.getDelivery_rang());
				paramMap.put("old_prod_num",goods.getOld_prod_num());
				paramMap.put("goods_num",goods.getGoods_num());
				paramMap.put("goods_name",goods.getGoods_name());
				paramMap.put("st_stock_amount",goods.getSt_stock_amount());
				paramMap.put("new_stock_amount",goods.getNew_stock_amount());
				paramMap.put("limit_amount",goods.getLimit_amount());
				paramMap.put("goods_desc",goods.getGoods_desc());
				paramMap.put("img",goods.getImg());
				paramMap.put("sell_price",goods.getSell_price());
				paramMap.put("is_promotion",goods.getIs_promotion());
				paramMap.put("is_on_shelf",goods.getIs_on_shelf());
				paramMap.put("is_main",goods.getIs_main());
				paramMap.put("is_hot",goods.getIs_hot());
				paramMap.put("is_limit_amount",goods.getIs_limit_amount());
				String on_shelf_dtFrom=request.getParameter("on_shelf_dtFrom");
				String on_shelf_dtTo=request.getParameter("on_shelf_dtTo");
				if(on_shelf_dtFrom!=null&&!on_shelf_dtFrom.equals(""))
				paramMap.put("on_shelf_dtFrom", sdf.parse(on_shelf_dtFrom));
				if(on_shelf_dtTo!=null&&!on_shelf_dtTo.equals(""))
				paramMap.put("on_shelf_dtTo", sdf.parse(on_shelf_dtTo));
				String under_shelf_dtFrom=request.getParameter("under_shelf_dtFrom");
				String under_shelf_dtTo=request.getParameter("under_shelf_dtTo");
				if(under_shelf_dtFrom!=null&&!under_shelf_dtFrom.equals(""))
				paramMap.put("under_shelf_dtFrom", sdf.parse(under_shelf_dtFrom));
				if(under_shelf_dtTo!=null&&!under_shelf_dtTo.equals(""))
				paramMap.put("under_shelf_dtTo", sdf.parse(under_shelf_dtTo));
				paramMap.put("status",goods.getStatus());
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
				
				paramMap.put("ctg_name",goods.getCtg_name());
				
				paramMap.put("pageviews",goods.getPageviews());
				paramMap.put("sales",goods.getSales());
				//search
				String searchtext=request.getParameter("searchtext");
				paramMap.put("searchtext", searchtext);
				
				List<Goods> list=iGoodsService.selectGoodsByParam(paramMap);
				int totalnumber=iGoodsService.selectCountGoodsByParam(paramMap);
				
				/////处理图片
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
				for(int index=0;index<list.size();index++){
					if(list.get(index).getImg()!=null){
						List<String> images=objectMapper.readValue(list.get(index).getImg(),javaType);
						if(images.size()>0){
							list.get(index).setFirstImg(images.get(0));
						}
					}
				}
				/////
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/newsGoods")
	@ResponseBody
	public Map news(HttpServletRequest request, HttpServletResponse response,Goods goods)
			throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String news_id=request.getParameter("news_id");
			if(news_id!=null){
				Map paramMap=new HashMap(); 
				String orderBy=request.getParameter("orderBy");
				if(orderBy!=null){
					paramMap.put("orderBy",orderBy); 
				}
				else{
					paramMap.put("orderBy","ID DESC"); 
				}
				paramMap.put("id",goods.getId());
				paramMap.put("c_id",goods.getC_id());
				paramMap.put("seller_id",goods.getSeller_id());
				paramMap.put("ctg_id",goods.getCtg_id());
				paramMap.put("postage_id",goods.getPostage_id());
				paramMap.put("delivery_rang",goods.getDelivery_rang());
				paramMap.put("old_prod_num",goods.getOld_prod_num());
				paramMap.put("goods_num",goods.getGoods_num());
				paramMap.put("goods_name",goods.getGoods_name());
				paramMap.put("st_stock_amount",goods.getSt_stock_amount());
				paramMap.put("new_stock_amount",goods.getNew_stock_amount());
				paramMap.put("limit_amount",goods.getLimit_amount());
				paramMap.put("goods_desc",goods.getGoods_desc());
				paramMap.put("img",goods.getImg());
				paramMap.put("sell_price",goods.getSell_price());
				paramMap.put("is_promotion",goods.getIs_promotion());
				paramMap.put("is_on_shelf",goods.getIs_on_shelf());
				paramMap.put("is_main",goods.getIs_main());
				paramMap.put("is_hot",goods.getIs_hot());
				paramMap.put("is_limit_amount",goods.getIs_limit_amount());
				String on_shelf_dtFrom=request.getParameter("on_shelf_dtFrom");
				String on_shelf_dtTo=request.getParameter("on_shelf_dtTo");
				if(on_shelf_dtFrom!=null&&!on_shelf_dtFrom.equals(""))
					paramMap.put("on_shelf_dtFrom", sdf.parse(on_shelf_dtFrom));
				if(on_shelf_dtTo!=null&&!on_shelf_dtTo.equals(""))
					paramMap.put("on_shelf_dtTo", sdf.parse(on_shelf_dtTo));
				String under_shelf_dtFrom=request.getParameter("under_shelf_dtFrom");
				String under_shelf_dtTo=request.getParameter("under_shelf_dtTo");
				if(under_shelf_dtFrom!=null&&!under_shelf_dtFrom.equals(""))
					paramMap.put("under_shelf_dtFrom", sdf.parse(under_shelf_dtFrom));
				if(under_shelf_dtTo!=null&&!under_shelf_dtTo.equals(""))
					paramMap.put("under_shelf_dtTo", sdf.parse(under_shelf_dtTo));
				paramMap.put("status",goods.getStatus());
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
				paramMap.put("ctg_name",goods.getCtg_name());
				paramMap.put("pageviews",goods.getPageviews());
				paramMap.put("sales",goods.getSales());
				paramMap.put("news_id",news_id);
				int totalnumber=iGoodsService.selectCountGoodsByParamNews(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",totalnumber); 
				List<Goods> list=iGoodsService.selectGoodsByParamNews(paramMap);
				
				
				/////处理图片
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
				for(int index=0;index<list.size();index++){
					List<String> images=objectMapper.readValue(list.get(index).getImg(),javaType);
					if(images.size()>0){
						list.get(index).setFirstImg(images.get(0));
					}
				}
				/////
				Map tempMap=new HashMap();
				resultMap.put("status", "0");
				tempMap.put("num", totalnumber);
				tempMap.put("data", list);
				resultMap.put("msg", tempMap);
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "资讯id不能为空！");
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
	@RequestMapping("/allGoods")
	@ResponseBody
	public Map all(HttpServletRequest request, HttpServletResponse response,Goods goods)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			 
			Map paramMap=new HashMap();
			String orderBy=request.getParameter("orderBy");
			if(orderBy!=null){
				paramMap.put("orderBy",orderBy); 
			}
			else{
				paramMap.put("orderBy","ID DESC"); 
			}
			paramMap.put("id",goods.getId());
			paramMap.put("c_id",goods.getC_id());
			paramMap.put("seller_id",goods.getSeller_id());
			paramMap.put("ctg_id",goods.getCtg_id());
			paramMap.put("postage_id",goods.getPostage_id());
			paramMap.put("delivery_rang",goods.getDelivery_rang());
			paramMap.put("old_prod_num",goods.getOld_prod_num());
			paramMap.put("goods_num",goods.getGoods_num());
			paramMap.put("goods_name",goods.getGoods_name());
			paramMap.put("st_stock_amount",goods.getSt_stock_amount());
			paramMap.put("new_stock_amount",goods.getNew_stock_amount());
			paramMap.put("limit_amount",goods.getLimit_amount());
			paramMap.put("goods_desc",goods.getGoods_desc());
			paramMap.put("img",goods.getImg());
			paramMap.put("sell_price",goods.getSell_price());
			paramMap.put("is_promotion",goods.getIs_promotion());
			paramMap.put("is_on_shelf",goods.getIs_on_shelf());
			paramMap.put("is_main",goods.getIs_main());
			paramMap.put("is_hot",goods.getIs_hot());
			paramMap.put("is_limit_amount",goods.getIs_limit_amount());
			String on_shelf_dtFrom=request.getParameter("on_shelf_dtFrom");
			String on_shelf_dtTo=request.getParameter("on_shelf_dtTo");
			if(on_shelf_dtFrom!=null&&!on_shelf_dtFrom.equals(""))
			paramMap.put("on_shelf_dtFrom", sdf.parse(on_shelf_dtFrom));
			if(on_shelf_dtTo!=null&&!on_shelf_dtTo.equals(""))
			paramMap.put("on_shelf_dtTo", sdf.parse(on_shelf_dtTo));
			String under_shelf_dtFrom=request.getParameter("under_shelf_dtFrom");
			String under_shelf_dtTo=request.getParameter("under_shelf_dtTo");
			if(under_shelf_dtFrom!=null&&!under_shelf_dtFrom.equals(""))
			paramMap.put("under_shelf_dtFrom", sdf.parse(under_shelf_dtFrom));
			if(under_shelf_dtTo!=null&&!under_shelf_dtTo.equals(""))
			paramMap.put("under_shelf_dtTo", sdf.parse(under_shelf_dtTo));
			paramMap.put("status",goods.getStatus());
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
			paramMap.put("ctg_name",goods.getCtg_name());
			paramMap.put("pageviews",goods.getPageviews());
			paramMap.put("sales",goods.getSales());
			int totalnumber=iGoodsService.selectCountGoodsByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			List<Goods> list=iGoodsService.selectGoodsByParam(paramMap);
			
			Map<String,List<Goods>> data= new HashMap<String,List<Goods>>();
			for(Goods g:list){
				String shopName=g.getShop_name();
				if(data.containsKey(shopName)){
					data.get(shopName).add(g);
				}
				else{
					List<Goods> temp= new ArrayList<Goods>();
					temp.add(g);
					data.put(shopName, temp);
				}
				
			}
			
			Map tempMap=new HashMap();
			resultMap.put("status", "0");
			tempMap.put("num", totalnumber);
			tempMap.put("data", data);
			resultMap.put("msg", tempMap);
			 
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/searchGoods")
	@ResponseBody
	public Map search(HttpServletRequest request, HttpServletResponse response,Goods goods)
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
				paramMap.put("id",goods.getId());
				paramMap.put("c_id",goods.getC_id());
				paramMap.put("seller_id",goods.getSeller_id());
				paramMap.put("ctg_id",goods.getCtg_id());
				paramMap.put("postage_id",goods.getPostage_id());
				paramMap.put("delivery_rang",goods.getDelivery_rang());
				paramMap.put("old_prod_num",goods.getOld_prod_num());
				paramMap.put("goods_num",goods.getGoods_num());
				paramMap.put("goods_name",goods.getGoods_name());
				paramMap.put("st_stock_amount",goods.getSt_stock_amount());
				paramMap.put("new_stock_amount",goods.getNew_stock_amount());
				paramMap.put("limit_amount",goods.getLimit_amount());
				paramMap.put("goods_desc",goods.getGoods_desc());
				paramMap.put("img",goods.getImg());
				paramMap.put("sell_price",goods.getSell_price());
				paramMap.put("is_promotion",goods.getIs_promotion());
				paramMap.put("is_on_shelf",goods.getIs_on_shelf());
				paramMap.put("is_main",goods.getIs_main());
				paramMap.put("is_hot",goods.getIs_hot());
				paramMap.put("is_limit_amount",goods.getIs_limit_amount());
				String on_shelf_dtFrom=request.getParameter("on_shelf_dtFrom");
				String on_shelf_dtTo=request.getParameter("on_shelf_dtTo");
				if(on_shelf_dtFrom!=null&&!on_shelf_dtFrom.equals(""))
				paramMap.put("on_shelf_dtFrom", sdf.parse(on_shelf_dtFrom));
				if(on_shelf_dtTo!=null&&!on_shelf_dtTo.equals(""))
				paramMap.put("on_shelf_dtTo", sdf.parse(on_shelf_dtTo));
				String under_shelf_dtFrom=request.getParameter("under_shelf_dtFrom");
				String under_shelf_dtTo=request.getParameter("under_shelf_dtTo");
				if(under_shelf_dtFrom!=null&&!under_shelf_dtFrom.equals(""))
				paramMap.put("under_shelf_dtFrom", sdf.parse(under_shelf_dtFrom));
				if(under_shelf_dtTo!=null&&!under_shelf_dtTo.equals(""))
				paramMap.put("under_shelf_dtTo", sdf.parse(under_shelf_dtTo));
				paramMap.put("status",goods.getStatus());
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
				
				paramMap.put("ctg_name",goods.getCtg_name());
				
				paramMap.put("pageviews",goods.getPageviews());
				paramMap.put("sales",goods.getSales());
				//search
				String search=request.getParameter("search");
				paramMap.put("search", search);
				
				List<Goods> list=iGoodsService.selectGoodsByParam(paramMap);
				int totalnumber=iGoodsService.selectCountGoodsByParam(paramMap);
				
				/////处理图片
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
				for(int index=0;index<list.size();index++){
					if(list.get(index).getImg()!=null){
						List<String> images=objectMapper.readValue(list.get(index).getImg(),javaType);
						if(images.size()>0){
							list.get(index).setFirstImg(images.get(0));
						}
					}
				}
				/////
				
				List<Map> resultList= new ArrayList<Map>();
				for(Goods g:list){
					String shopName=g.getShop_name();
					String shopId=g.getSeller_id()+"";
					boolean flag = false;
					/*for(Entry<String,Object> entry :data.entrySet()){
						if(entry.ge)
					}*/
					for(int index=0;index<resultList.size();index++){
						Map map = resultList.get(index);
						if(map.get("shopName").equals(shopName)){
							flag=true;
							List<Goods> tempList= (List<Goods>) map.get("goods");
							tempList.add(g);
						}
					}
					
					if(!flag){
						Map<String,Object> data= new HashMap<String,Object>();
						data.put("shopId", shopId);
						data.put("shopName", shopName);
						List<Goods> tempList= new ArrayList<Goods>();
						tempList.add(g);
						data.put("goods", tempList);
						resultList.add(data);
					}
					  
				}
				Map tempMap=new HashMap();
				resultMap.put("status", "0");
				tempMap.put("num", totalnumber);
				tempMap.put("data", resultList);
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
