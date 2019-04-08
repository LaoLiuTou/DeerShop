package com.deershop.controller.orders;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deershop.controller.PushUtils;
import com.deershop.model.order_item.Order_item;
import com.deershop.model.orders.Orders;
import com.deershop.service.order_item.IOrder_itemService;
import com.deershop.service.orders.IOrdersService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class OrdersController {
	@Autowired
	private IOrdersService iOrdersService;
	@Autowired
	private IOrder_itemService iOrder_itemService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addOrders")
	@ResponseBody
	public Map add(HttpServletRequest request,Orders orders){
		Map resultMap=new HashMap();
		try {
			String order_items=request.getParameter("order_items");
			iOrdersService.addOrders(orders,order_items);
			resultMap.put("status", "0");
			resultMap.put("msg", orders.getId());
			logger.info("新建成功，主键："+orders.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdOrders")
	@ResponseBody
	public Map muladd(@RequestBody List<Orders> list,HttpServletRequest request){
		Map resultMap=new HashMap();
		try {
			List<String> ids=new ArrayList<String>();
			for(Orders o:list){
				iOrdersService.addOrders(o,o.getOrder_item());
				ids.add(o.getId()+"");
				
				//推送
				PushUtils.push("s_"+o.getSeller_id(), o.getId()+"","1","order", "您有新订单，请及时处理");
			}
			resultMap.put("status", "0");
			resultMap.put("msg", StringUtils.join(ids,","));
			logger.info("新建成功，主键："+StringUtils.join(ids,","));
			
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteOrders")
	@ResponseBody
	public Map delete(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iOrdersService.deleteOrders(orders.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+orders.getId());
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
	@RequestMapping("/selectOrders")
	@ResponseBody
	public Map select(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Orders resultSelect=iOrdersService.selectOrdersById(orders.getId()+"");
				if(resultSelect!=null){
					Map paramMap=new HashMap();
					paramMap.put("orders_id",resultSelect.getId());
					int number=iOrdersService.selectCountOrdersByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",number); 
					List<Order_item> item_list=iOrder_itemService.selectOrder_itemByParam(paramMap);
					/////处理图片
					ObjectMapper objectMapper = new ObjectMapper();
					JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
					for(int i=0;i<item_list.size();i++){
						if(item_list.get(i).getImg()!=null){
							List<String> images=objectMapper.readValue(item_list.get(i).getImg(),javaType);
							if(images.size()>0){
								item_list.get(i).setFirstImg(images.get(0));
							}
						}
					}
					resultSelect.setOrder_item(item_list);
					
					resultMap.put("status", "0");
					resultMap.put("msg", resultSelect);
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "订单不存在或者已经删除！");
				}
				
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
	@RequestMapping("/selectOrdersApp")
	@ResponseBody
	public Map selectApp(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Orders resultSelect=iOrdersService.selectordersByIdApp(orders.getId()+"");
				if(resultSelect!=null){
					Map paramMap=new HashMap();
					paramMap.put("orders_id",resultSelect.getId());
					int number=iOrdersService.selectCountOrdersByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",number); 
					List<Order_item> item_list=iOrder_itemService.selectOrder_itemByParam(paramMap);
					/////处理图片
					ObjectMapper objectMapper = new ObjectMapper();
					JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
					for(int i=0;i<item_list.size();i++){
						if(item_list.get(i).getImg()!=null){
							List<String> images=objectMapper.readValue(item_list.get(i).getImg(),javaType);
							if(images.size()>0){
								item_list.get(i).setFirstImg(images.get(0));
							}
						}
					}
					resultSelect.setOrder_item(item_list);
					
					resultMap.put("status", "0");
					resultMap.put("msg", resultSelect);
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "订单不存在或者已经删除！");
				}
				
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
	@RequestMapping("/selectCdOrders")
	@ResponseBody
	public Map selectCd(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Orders resultSelect=iOrdersService.selectCdOrdersById(orders.getId()+"");
				if(resultSelect!=null){
					Map paramMap=new HashMap();
					paramMap.put("orders_id",resultSelect.getId());
					int number=iOrdersService.selectCountOrdersByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",number); 
					List<Order_item> item_list=iOrder_itemService.selectOrder_itemByParam(paramMap);
					/////处理图片
					ObjectMapper objectMapper = new ObjectMapper();
					JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
					for(int i=0;i<item_list.size();i++){
						if(item_list.get(i).getImg()!=null){
							List<String> images=objectMapper.readValue(item_list.get(i).getImg(),javaType);
							if(images.size()>0){
								item_list.get(i).setFirstImg(images.get(0));
							}
						}
					}
					resultSelect.setOrder_item(item_list);
					
					resultMap.put("status", "0");
					resultMap.put("msg", resultSelect);
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "订单不存在！");
				}
				
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
	@RequestMapping("/updateOrders")
	@ResponseBody
	public Map update(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iOrdersService.updateOrders(orders);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+orders.getId());
				
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
	@RequestMapping("/updateOrdersByOrdernum")
	@ResponseBody
	public Map updateByOrdernum(Orders orders){
		Map resultMap=new HashMap();
		try {
			if(orders.getOrder_num()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iOrdersService.updateByOrdernum(orders);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+orders.getId());
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
	@RequestMapping("/listOrders")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Orders orders)
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
				paramMap.put("id",orders.getId());
				paramMap.put("c_id",orders.getC_id());
				paramMap.put("seller_id",orders.getSeller_id());
				paramMap.put("buyer_id",orders.getBuyer_id());
				paramMap.put("all_price",orders.getAll_price());
				paramMap.put("all_goods_price",orders.getAll_goods_price());
				paramMap.put("postage_price",orders.getPostage_price());
				paramMap.put("promotion_price",orders.getPromotion_price());
				paramMap.put("discount_price",orders.getDiscount_price());
				paramMap.put("full_discount_price",orders.getFull_discount_price());
				paramMap.put("concession_price",orders.getConcession_price());
				paramMap.put("real_price",orders.getReal_price());
				paramMap.put("point",orders.getPoint());
				paramMap.put("pay_type",orders.getPay_type());
				paramMap.put("pay_info",orders.getPay_info());
				paramMap.put("is_pay",orders.getIs_pay());
				paramMap.put("is_split",orders.getIs_split());
				paramMap.put("is_deliver",orders.getIs_deliver());
				paramMap.put("is_confirm",orders.getIs_confirm());
				paramMap.put("is_evaluate",orders.getIs_evaluate());
				paramMap.put("is_cancel",orders.getIs_cancel());
				paramMap.put("is_close",orders.getIs_close());
				paramMap.put("is_delete",orders.getIs_delete());
				paramMap.put("is_delete_app",orders.getIs_delete_app());
				paramMap.put("order_num",orders.getOrder_num());
				String create_dtFrom=request.getParameter("create_dtFrom");
				String create_dtTo=request.getParameter("create_dtTo");
				if(create_dtFrom!=null&&!create_dtFrom.equals(""))
				paramMap.put("create_dtFrom", sdf.parse(create_dtFrom));
				if(create_dtTo!=null&&!create_dtTo.equals(""))
				paramMap.put("create_dtTo", sdf.parse(create_dtTo));
				String pay_dtFrom=request.getParameter("pay_dtFrom");
				String pay_dtTo=request.getParameter("pay_dtTo");
				if(pay_dtFrom!=null&&!pay_dtFrom.equals(""))
				paramMap.put("pay_dtFrom", sdf.parse(pay_dtFrom));
				if(pay_dtTo!=null&&!pay_dtTo.equals(""))
				paramMap.put("pay_dtTo", sdf.parse(pay_dtTo));
				String complete_dtFrom=request.getParameter("complete_dtFrom");
				String complete_dtTo=request.getParameter("complete_dtTo");
				if(complete_dtFrom!=null&&!complete_dtFrom.equals(""))
				paramMap.put("complete_dtFrom", sdf.parse(complete_dtFrom));
				if(complete_dtTo!=null&&!complete_dtTo.equals(""))
				paramMap.put("complete_dtTo", sdf.parse(complete_dtTo));
				paramMap.put("status",orders.getStatus());
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
				List<Orders> list=iOrdersService.selectOrdersByParam(paramMap);
				int totalnumber=iOrdersService.selectCountOrdersByParam(paramMap);
				
				for(int index=0;index<list.size();index++){
					paramMap=new HashMap();
					paramMap.put("orders_id",list.get(index).getId());
					int number=iOrdersService.selectCountOrdersByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",number); 
					List<Order_item> item_list=iOrder_itemService.selectOrder_itemByParam(paramMap);
					/////处理图片
					ObjectMapper objectMapper = new ObjectMapper();
					JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
					for(int i=0;i<item_list.size();i++){
						if(item_list.get(i).getImg()!=null){
							List<String> images=objectMapper.readValue(item_list.get(i).getImg(),javaType);
							if(images.size()>0){
								item_list.get(i).setFirstImg(images.get(0));
							}
						}
						
					}
					list.get(index).setOrder_item(item_list);
				}
				
				
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
	@RequestMapping("/remindOrders")
	@ResponseBody
	public Map remind(HttpServletRequest request){
		Map resultMap=new HashMap();
		try {
			
			String order_id=request.getParameter("order_id");
			String order_num=request.getParameter("order_num");
			String seller_id=request.getParameter("seller_id");
			if(order_id==null||order_num==null||seller_id==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				//推送
				PushUtils.push("s_"+seller_id, order_id+"","1","order", "收到提醒发货消息，订单号("+order_num+")");
				resultMap.put("status", "0");
				resultMap.put("msg", "提醒发货成功！");
				logger.info("提醒发货成功，order_num："+order_num);
				
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "更新失败！");
			logger.info("更新失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	 
}
