package com.deershop.controller.order_item;
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
import com.deershop.service.order_item.IOrder_itemService;
import com.deershop.model.order_item.Order_item;
@Controller
public class Order_itemController {
	@Autowired
	private IOrder_itemService iOrder_itemService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addOrder_item")
	@ResponseBody
	public Map add(Order_item order_item){
		Map resultMap=new HashMap();
		try {
			iOrder_itemService.addOrder_item(order_item);
			resultMap.put("status", "0");
			resultMap.put("msg", order_item.getId());
			logger.info("新建成功，主键："+order_item.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdOrder_item")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Order_item order_item){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Order_item.class);
			List<Order_item> list = (List<Order_item>)objectMapper.readValue(data, javaType);
			iOrder_itemService.muladdOrder_item(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+order_item.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteOrder_item")
	@ResponseBody
	public Map delete(Order_item order_item){
		Map resultMap=new HashMap();
		try {
			if(order_item.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iOrder_itemService.deleteOrder_item(order_item.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+order_item.getId());
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
	@RequestMapping("/selectOrder_item")
	@ResponseBody
	public Map select(Order_item order_item){
		Map resultMap=new HashMap();
		try {
			if(order_item.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Order_item resultSelect=iOrder_itemService.selectOrder_itemById(order_item.getId()+"");
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
	@RequestMapping("/updateOrder_item")
	@ResponseBody
	public Map update(Order_item order_item){
		Map resultMap=new HashMap();
		try {
			if(order_item.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iOrder_itemService.updateOrder_item(order_item);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+order_item.getId());
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
	@RequestMapping("/listOrder_item")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Order_item order_item)
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
				paramMap.put("id",order_item.getId());
				paramMap.put("c_id",order_item.getC_id());
				paramMap.put("orders_id",order_item.getOrders_id());
				paramMap.put("goods_id",order_item.getGoods_id());
				paramMap.put("point",order_item.getPoint());
				paramMap.put("discount_id",order_item.getDiscount_id());
				paramMap.put("promotion_id",order_item.getPromotion_id());
				paramMap.put("full_id",order_item.getFull_id());
				paramMap.put("amount",order_item.getAmount());
				paramMap.put("sell_price",order_item.getSell_price());
				paramMap.put("real_price",order_item.getReal_price());
				paramMap.put("discount_price",order_item.getDiscount_price());
				paramMap.put("full_discount_price",order_item.getFull_discount_price());
				paramMap.put("concession_price",order_item.getConcession_price());
				paramMap.put("promotion_price",order_item.getPromotion_price());
				paramMap.put("is_deliver",order_item.getIs_deliver());
				paramMap.put("status",order_item.getStatus());
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
				List<Order_item> list=iOrder_itemService.selectOrder_itemByParam(paramMap);
				int totalnumber=iOrder_itemService.selectCountOrder_itemByParam(paramMap);
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
