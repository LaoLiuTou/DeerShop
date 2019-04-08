package com.deershop.controller;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deershop.dao.orders.IOrdersMapper;
import com.deershop.dao.returns.IReturnsMapper;
import com.deershop.model.goods.Goods;
import com.deershop.model.order_item.Order_item;
import com.deershop.model.orders.Orders;
import com.deershop.model.pay.Pay;
import com.deershop.model.returns.Returns;
import com.deershop.service.goods.IGoodsService;
import com.deershop.service.order_item.IOrder_itemService;
import com.deershop.service.orders.IOrdersService;
import com.deershop.service.pay.IPayService;
import com.deershop.service.returns.IReturnsService;
import com.deershop.utils.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component 
public class TimeUtils {

	@Autowired
	private IOrdersService iOrdersService;
	@Autowired
	private IReturnsService iReturnsService;
	@Autowired
	private IReturnsMapper iReturnsMapper;
	@Autowired
	private IOrdersMapper iOrdersMapper;
	@Autowired
	private IPayService iPayService;
	@Autowired
	private IGoodsService iGoodsService;
	@Autowired
	private IOrder_itemService iOrder_itemService;
	Logger logger = Logger.getLogger("DeerShopLogger");
    @SuppressWarnings({ "rawtypes", "unchecked" })
	//@Scheduled(cron="0 0 0/1 * * ? ")  
    @Scheduled(cron="0 0/10 * * * ? ")  
    public void closeOrder(){  
    	try {
    		logger.info("查询待支付订单！");
			//待支付
    		Map paramMap=new HashMap();
			paramMap.put("is_pay","N");
			paramMap.put("is_deliver","N");
			paramMap.put("is_confirm","N");
			paramMap.put("is_cancel","N");
			paramMap.put("is_close","N");
			paramMap.put("is_delete","N");
			int totalnumber=iOrdersService.selectCountOrdersByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			paramMap.put("orderBy","ID ASC");
			List<Orders> list=iOrdersService.selectOrdersByParam(paramMap);
			for(Orders orders:list){
				if(orders.getTimediff()!=null){
					int timediff= Integer.parseInt(orders.getTimediff());
					if(timediff>=24){
						Orders temp = new Orders();
						temp.setId(orders.getId());
						temp.setIs_close("Y");
						temp.setStatus("已关闭");
						iOrdersService.updateOrders(temp);
						logger.info("关闭订单，ID:"+orders.getId());
						//商品数量++
						paramMap=new HashMap();
						paramMap.put("orders_id",orders.getId());
						totalnumber=iOrder_itemService.selectCountOrder_itemByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						List<Order_item> listOi=iOrder_itemService.selectOrder_itemByParam(paramMap);
						for(Order_item oi:listOi){
							Goods goods=iGoodsService.selectGoodsById(oi.getGoods_id()+"");
							if(goods!=null){
								Goods tempGoods= new Goods();
								tempGoods.setId(goods.getId());
								tempGoods.setNew_stock_amount(goods.getNew_stock_amount()+oi.getAmount());
								iGoodsService.updateGoods(tempGoods);
							}
						}
					}
				}
			} 
    		
		} catch (Exception e) {
			logger.info("计时器查询待支付订单出错！");
			e.printStackTrace();
		}
    	 
    } 
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Scheduled(cron="0 0 0/1 * * ? ")  
    public void completeOrder(){  
    	try {
    		logger.info("查询未确认收货订单！");
    		//确认收货
    		Map paramMap=new HashMap();
    		paramMap.put("is_pay","Y");
    		paramMap.put("is_deliver","Y");
    		paramMap.put("is_confirm","N");
    		paramMap.put("is_cancel","N");
    		paramMap.put("is_close","N");
    		paramMap.put("is_delete","N");
    		int totalnumber=iOrdersService.selectCountOrdersByParam(paramMap);
    		paramMap.put("fromPage",0);
    		paramMap.put("toPage",totalnumber); 
    		paramMap.put("orderBy","ID ASC");
    		List<Orders> list=iOrdersService.selectOrdersByParam(paramMap);
    		for(Orders orders:list){
    			if(orders.getTimediff()!=null){
    				int timediff= Integer.parseInt(orders.getTimediff());
    				if(timediff>=24*15){
    					Orders temp = new Orders();
    					temp.setId(orders.getId());
    					temp.setIs_confirm("Y");
    					temp.setStatus("已完成");
    					//temp.setComplete_dt(new Date());
    					iOrdersService.updateOrders(temp);
    					logger.info("自动完成订单，ID:"+orders.getId());
    				}
    			}
    		} 
    		
    	} catch (Exception e) {
    		logger.info("计时器查询未确认收货订单出错！");
    		e.printStackTrace();
    	}
    	
    } 
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Scheduled(cron="0 0/10 * * * ? ") 
    public void completeReturn(){  
    	try {
    		logger.info("查询退款订单！");
    		//退款
    		Map paramMap=new HashMap();
    		paramMap.put("is_pay","Y");
    		paramMap.put("is_deliver","N");
    		paramMap.put("return_ctg","退款");
    		paramMap.put("status","待退款");
    		int totalnumber=iReturnsService.selectCountReturnsByParam(paramMap);
    		paramMap.put("fromPage",0);
    		paramMap.put("toPage",totalnumber); 
    		paramMap.put("orderBy","ID ASC");
    		List<Returns> list=iReturnsService.selectReturnsByParam(paramMap);
    		for(Returns returns:list){
    			if(returns.getTimediff()!=null){
    				int timediff= Integer.parseInt(returns.getTimediff());
    				if(timediff>=30){
    					//////////////////////////////////
    					///////执行退款操作/////////////////
    					/////////////////////////////////
    					Properties properties = new Properties();
    					String base = PushUtils.class.getResource("/")
    							.getPath();
    					properties.load(new FileInputStream(base
    								+ "jdbc/jdbc.properties"));
    					String pushUrl=properties.getProperty("refundUrl").trim();
    					Map<String,String> map=new HashMap<String,String>();
    					
    					//获取支付订单号
    					paramMap=new HashMap();
    					paramMap.put("orderBy","ID DESC"); 
    		    		paramMap.put("order_ids",returns.getOrders_id());
    		    		paramMap.put("fromPage",0);
    		    		paramMap.put("toPage",1); 
    		    		List<Pay> payList = iPayService.selectPayByParam(paramMap);
    		    		String out_trade_no="";
    		    		if(payList.size()>0){
    		    			out_trade_no=payList.get(0).getPay_id();
    		    		}
    		    		
    		    		if(out_trade_no!=null&&returns.getReturn_price()!=null){
    		    			if(Float.parseFloat(returns.getReturn_price())>0) {
    		    				map.put("out_trade_no", out_trade_no);
            					map.put("refund_fee", returns.getReturn_price());
            					String result=HttpRequest.postMap(pushUrl, null,map);
            					logger.info(returns.getId()+"退款结果："+result);
            					ObjectMapper mapper = new ObjectMapper();
            			        Map readValue = mapper.readValue(result, Map.class);
            			        if(readValue.containsKey("return_code")&&readValue.get("return_code").equals("SUCCESS")
            			        		&&readValue.containsKey("result_code")&&readValue.get("result_code").equals("SUCCESS")){
            			        	Returns temp = new Returns();
                					temp.setId(returns.getId());
                					temp.setStatus("退款成功");
                					temp.setReturn_dt(new Date());
                					temp.setAgree_dt(new Date());
                					temp.setIs_return("Y");
                					iReturnsService.updateReturns(temp);
                					logger.info("自动完成退款，ID:"+returns.getId());
                					///所有退款完成后订单设置为完成
                					paramMap=new HashMap();
                					paramMap.put("orders_id",returns.getOrders_id()+""); 
                					int returnNum = iReturnsMapper.selectCountreturnsByParam(paramMap);
                					paramMap.put("status","退款成功");
                					int completeNum = iReturnsMapper.selectCountreturnsByParam(paramMap);
                					if(returnNum==completeNum){
                						Orders orders = new Orders();
                						orders.setId(returns.getOrders_id());
                						orders.setIs_close("Y");
                						orders.setStatus("已关闭");
                						orders.setComplete_dt(new Date());
                						iOrdersMapper.updateorders(orders);
                					}
            			        }
            			        else{
            			        	if(readValue.containsKey("err_code_des")&&readValue.containsKey("return_msg")){
            			        		logger.info("自动退款失败:"+readValue.get("err_code_des")+";"+readValue.get("return_msg"));
            			        	}
            			        	else{
            			        		logger.info("自动退款失败:"+"返回错误！");
            			        	}
            			        }
    		    			}
    		    			else{
    		    				logger.info("自动退款失败，ID:"+returns.getId()+",金额错误！");
    		    			}
    		    			
    		    		}
    		    		else{
    		    			logger.info("自动退款失败，ID:"+returns.getId()+",参数错误！");
    		    		}
    					
    			        
    					
    					
    				}
    			}
    		} 
    		
    		
    		//付款失败
    		paramMap=new HashMap();
    		paramMap.put("is_agree","Y");
    		paramMap.put("status","退款中");
    		paramMap.put("is_return","N");
    		totalnumber=iReturnsService.selectCountReturnsByParam(paramMap);
    		paramMap.put("fromPage",0);
    		paramMap.put("toPage",totalnumber); 
    		paramMap.put("orderBy","ID ASC");
    		list=iReturnsService.selectReturnsByParam(paramMap);
    		for(Returns returns:list){
				//////////////////////////////////
				///////执行退款操作/////////////////
				/////////////////////////////////
				Properties properties = new Properties();
				String base = PushUtils.class.getResource("/")
						.getPath();
				properties.load(new FileInputStream(base
							+ "jdbc/jdbc.properties"));
				String pushUrl=properties.getProperty("refundUrl").trim();
				Map<String,String> map=new HashMap<String,String>();
				
				//获取支付订单号
				paramMap=new HashMap();
				paramMap.put("orderBy","ID DESC"); 
	    		paramMap.put("order_ids",returns.getOrders_id());
	    		paramMap.put("fromPage",0);
	    		paramMap.put("toPage",1); 
	    		List<Pay> payList = iPayService.selectPayByParam(paramMap);
	    		String out_trade_no="";
	    		if(payList.size()>0){
	    			out_trade_no=payList.get(0).getPay_id();
	    		}
				map.put("out_trade_no", out_trade_no);
				map.put("refund_fee", returns.getReturn_price());
				String result=HttpRequest.postMap(pushUrl, null,map);
				ObjectMapper mapper = new ObjectMapper();
		        Map readValue = mapper.readValue(result, Map.class);
		        if(readValue.containsKey("return_code")&&readValue.get("return_code").equals("SUCCESS")
			        	&&readValue.containsKey("result_code")&&readValue.get("result_code").equals("SUCCESS")){	
		        	Returns temp = new Returns();
					temp.setId(returns.getId());
					temp.setStatus("退款成功");
					temp.setReturn_dt(new Date());
					temp.setIs_return("Y");
					iReturnsMapper.updatereturns(temp);
					
					///所有退款完成后订单设置为完成
					paramMap=new HashMap();
					paramMap.put("orders_id",returns.getOrders_id()+""); 
					int returnNum = iReturnsMapper.selectCountreturnsByParam(paramMap);
					paramMap.put("status","退款成功");
					int completeNum = iReturnsMapper.selectCountreturnsByParam(paramMap);
					if(returnNum==completeNum){
						Orders orders = new Orders();
						orders.setId(returns.getOrders_id());
						orders.setStatus("已关闭");
						orders.setIs_close("Y");
						orders.setComplete_dt(new Date());
						iOrdersMapper.updateorders(orders);
					}
					logger.info("自动完成退款，ID:"+returns.getId());
		        }
		        else{
		        	if(readValue.containsKey("err_code_des")&&readValue.containsKey("return_msg")){
		        		logger.info("自动退款失败:"+readValue.get("err_code_des")+";"+readValue.get("return_msg"));
		        	}
		        	else{
		        		logger.info("自动退款失败:"+"返回错误！");
		        	}
		        }
    			  
    		} 
    	} catch (Exception e) {
    		logger.info("计时器查询退款订单出错！");
    		e.printStackTrace();
    	}
    	
    } 
}
