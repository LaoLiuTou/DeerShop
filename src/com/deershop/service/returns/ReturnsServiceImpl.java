package com.deershop.service.returns;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.deershop.controller.PushUtils;
import com.deershop.dao.goods.IGoodsMapper;
import com.deershop.dao.income_dtl.IIncome_dtlMapper;
import com.deershop.dao.order_item.IOrder_itemMapper;
import com.deershop.dao.order_num.IOrder_numMapper;
import com.deershop.dao.orders.IOrdersMapper;
import com.deershop.dao.returns.IReturnsMapper;
import com.deershop.dao.seller.ISellerMapper;
import com.deershop.model.goods.Goods;
import com.deershop.model.income_dtl.Income_dtl;
import com.deershop.model.order_item.Order_item;
import com.deershop.model.order_num.Order_num;
import com.deershop.model.orders.Orders;
import com.deershop.model.pay.Pay;
import com.deershop.model.returns.Returns;
import com.deershop.model.seller.Seller;
import com.deershop.service.pay.IPayService;
import com.deershop.utils.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ReturnsServiceImpl  implements IReturnsService {

	@Autowired
	private IReturnsMapper iReturnsMapper;
	@Autowired
	private IOrder_numMapper iOrder_numMapper;
	@Autowired
	private IOrdersMapper iOrdersMapper;
	@Autowired
	private IIncome_dtlMapper iIncome_dtlMapper;
	@Autowired
	private ISellerMapper iSellerMapper;
	@Autowired
	private IGoodsMapper iGoodsMapper;
	@Autowired
	private IOrder_itemMapper iOrder_itemMapper;
	@Autowired
	private IPayService iPayService;
	/**
	* 通过id选取
	* @return
	*/
	public Returns selectReturnsById(String id){
		return iReturnsMapper.selectreturnsById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Returns> selectReturnsByParam(Map paramMap){ 
		return iReturnsMapper.selectreturnsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountReturnsByParam(Map paramMap){ 
		return iReturnsMapper.selectCountreturnsByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public  int updateReturns(Returns returns){
		Logger logger = Logger.getLogger("DeerShopLogger");
		int result=0;
		if(returns.getIs_agree()!=null&&returns.getIs_agree().equals("Y")){
			returns.setAgree_dt(new Date());
		}
		else if(returns.getIs_agree()!=null&&returns.getIs_agree().equals("N")){
			returns.setAgree_dt(new Date());
			returns.setReturn_dt(new Date());
		}
		else if(returns.getIs_send()!=null&&returns.getIs_send().equals("Y")){
			returns.setSend_dt(new Date());
		}
		else if(returns.getIs_take()!=null&&returns.getIs_take().equals("Y")){
			returns.setTake_dt(new Date());
		}
		result=iReturnsMapper.updatereturns(returns);
		if(result>0){
			//推送
			String message="";
			Returns pushReturns=iReturnsMapper.selectreturnsById(returns.getId()+"");
			try {
				if(returns.getIs_agree()!=null&&returns.getIs_agree().equals("N")){
					message=pushReturns.getShop_name()+"拒绝了您的退款申请，请及时处理！";
					PushUtils.push("b_"+pushReturns.getBuyer_id(), pushReturns.getId()+"","2","return", message);
					
				}	
				else if(returns.getIs_agree()!=null&&returns.getIs_agree().equals("Y")){
					message=pushReturns.getShop_name()+"已经同意您的退款申请，请及时处理！";
					PushUtils.push("b_"+pushReturns.getBuyer_id(), pushReturns.getId()+"","2","return", message);
					if(pushReturns.getReturn_ctg()!=null&&pushReturns.getReturn_ctg().equals("退款")){
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
						Map paramMap=new HashMap();
						paramMap.put("orderBy","ID DESC"); 
			    		paramMap.put("order_ids",pushReturns.getOrders_id());
			    		paramMap.put("fromPage",0);
			    		paramMap.put("toPage",1); 
			    		List<Pay> payList = iPayService.selectPayByParam(paramMap);
			    		String out_trade_no="";
			    		if(payList.size()>0){
			    			out_trade_no=payList.get(0).getPay_id();
			    		}
						map.put("out_trade_no", out_trade_no);
						map.put("refund_fee", pushReturns.getReturn_price());
						String postResult=HttpRequest.postMap(pushUrl, null,map);
						ObjectMapper mapper = new ObjectMapper();
				        Map readValue = mapper.readValue(postResult, Map.class);
				        if(readValue.get("result_code").equals("SUCCESS")){
				        	message="您的退货订单("+pushReturns.getReturn_code()+")已完成！";
							PushUtils.push("b_"+pushReturns.getBuyer_id(), pushReturns.getId()+"","2","return", message);
							PushUtils.push("s_"+pushReturns.getSeller_id(), pushReturns.getId()+"","1","return", message);
							//资金收入支出
							Income_dtl income_dtl=new Income_dtl();
							income_dtl.setC_id(Long.parseLong("0"));
							income_dtl.setIncome_item("退款");
							income_dtl.setIncome_type("支出");
							income_dtl.setOperate_dt(new Date());
							income_dtl.setOrders_id(pushReturns.getId());
							income_dtl.setPrice(pushReturns.getReturn_price());
							income_dtl.setSeller_id(pushReturns.getSeller_id());
							iIncome_dtlMapper.addincome_dtl(income_dtl);
							
							//状态
							Returns temp = new Returns();
							temp.setId(pushReturns.getId());
							temp.setStatus("退款成功");
							temp.setReturn_dt(new Date());
							temp.setIs_return("Y");
							iReturnsMapper.updatereturns(temp);
							
							//商家信息
							Seller seller = iSellerMapper.selectsellerById(pushReturns.getSeller_id()+"");
							Float price=Float.parseFloat(pushReturns.getReturn_price());
							Seller tempSeller = new Seller();
							tempSeller.setId(seller.getId());
							tempSeller.setAll_income((Float.parseFloat(seller.getAll_income())-price)+"");
							tempSeller.setNo_clear_income((Float.parseFloat(seller.getNo_clear_income())-price)+"");
							iSellerMapper.updateseller(tempSeller);
							//商品数量++
							Order_item oi=iOrder_itemMapper.selectorder_itemById(pushReturns.getOrder_item_ids());
							Goods goods=iGoodsMapper.selectgoodsById(oi.getGoods_id()+"");
							if(goods!=null){
								Goods tempGoods= new Goods();
								tempGoods.setId(goods.getId());
								tempGoods.setNew_stock_amount(goods.getNew_stock_amount()+oi.getAmount());
								tempGoods.setSt_stock_amount(goods.getSt_stock_amount()+oi.getAmount());
								iGoodsMapper.updategoods(tempGoods);
							}
							logger.info("完成退款，ID:"+pushReturns.getId());
				        }
				        else{
				        	//状态
							Returns temp = new Returns();
							temp.setId(pushReturns.getId());
							temp.setIs_return("N");
							iReturnsMapper.updatereturns(temp);
				        	logger.info("退款失败:"+readValue.get("err_code_des"));
				        }
    
			
					}
				}
				else if(returns.getIs_send()!=null&&returns.getIs_send().equals("Y")){
					message="您的退货订单("+pushReturns.getReturn_code()+")已发货，请及时处理！";
					PushUtils.push("s_"+pushReturns.getSeller_id(), pushReturns.getId()+"","1","return", message);
				} 
				else if(returns.getIs_take()!=null&&returns.getIs_take().equals("Y")){ 
					if(pushReturns.getReturn_ctg()!=null&&pushReturns.getReturn_ctg().equals("退货退款")){
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
						Map paramMap=new HashMap();
						paramMap.put("orderBy","ID DESC"); 
			    		paramMap.put("order_ids",pushReturns.getOrders_id());
			    		paramMap.put("fromPage",0);
			    		paramMap.put("toPage",1); 
			    		List<Pay> payList = iPayService.selectPayByParam(paramMap);
			    		String out_trade_no="";
			    		if(payList.size()>0){
			    			out_trade_no=payList.get(0).getPay_id();
			    		}
						map.put("out_trade_no", out_trade_no);
						map.put("refund_fee", pushReturns.getReturn_price());
						String postResult=HttpRequest.postMap(pushUrl, null,map);
						ObjectMapper mapper = new ObjectMapper();
				        Map readValue = mapper.readValue(postResult, Map.class);
				        if(readValue.get("result_code").equals("SUCCESS")){
				        	message="您的退货订单("+pushReturns.getReturn_code()+")已完成！";
							PushUtils.push("b_"+pushReturns.getBuyer_id(), pushReturns.getId()+"","2","return", message);
							PushUtils.push("s_"+pushReturns.getSeller_id(), pushReturns.getId()+"","1","return", message);
							//资金收入支出
							Income_dtl income_dtl=new Income_dtl();
							income_dtl.setC_id(Long.parseLong("0"));
							income_dtl.setIncome_item("退款");
							income_dtl.setIncome_type("支出");
							income_dtl.setOperate_dt(new Date());
							income_dtl.setOrders_id(pushReturns.getId());
							income_dtl.setPrice(pushReturns.getReturn_price());
							income_dtl.setSeller_id(pushReturns.getSeller_id());
							iIncome_dtlMapper.addincome_dtl(income_dtl);
							
							//状态
							Returns temp = new Returns();
							temp.setId(pushReturns.getId());
							temp.setStatus("退款成功");
							temp.setReturn_dt(new Date());
							temp.setIs_return("Y");
							iReturnsMapper.updatereturns(temp);
							
							//商家信息
							Seller seller = iSellerMapper.selectsellerById(pushReturns.getSeller_id()+"");
							Float price=Float.parseFloat(pushReturns.getReturn_price());
							Seller tempSeller = new Seller();
							tempSeller.setId(seller.getId());
							tempSeller.setAll_income((Float.parseFloat(seller.getAll_income())-price)+"");
							tempSeller.setNo_clear_income((Float.parseFloat(seller.getNo_clear_income())-price)+"");
							iSellerMapper.updateseller(tempSeller);
							//商品数量++
							Order_item oi=iOrder_itemMapper.selectorder_itemById(pushReturns.getOrder_item_ids());
							Goods goods=iGoodsMapper.selectgoodsById(oi.getGoods_id()+"");
							if(goods!=null){
								Goods tempGoods= new Goods();
								tempGoods.setId(goods.getId());
								tempGoods.setNew_stock_amount(goods.getNew_stock_amount()+oi.getAmount());
								tempGoods.setSt_stock_amount(goods.getSt_stock_amount()+oi.getAmount());
								iGoodsMapper.updategoods(tempGoods);
							}
							logger.info("完成退款，ID:"+pushReturns.getId());
				        }
				        else{
				        	//状态
							Returns temp = new Returns();
							temp.setId(pushReturns.getId());
							temp.setIs_return("N");
							iReturnsMapper.updatereturns(temp);
				        	logger.info("退款失败:"+readValue.get("err_code_des"));
				        }
    
					}
					
					
				}
				else if(returns.getStatus()!=null&&returns.getStatus().equals("退款成功")){
					message="您的退货订单("+pushReturns.getReturn_code()+")已完成！";
					PushUtils.push("b_"+pushReturns.getBuyer_id(), pushReturns.getId()+"","2","return", message);
					PushUtils.push("s_"+pushReturns.getSeller_id(), pushReturns.getId()+"","1","return", message);
					//资金收入支出
					Income_dtl income_dtl=new Income_dtl();
					income_dtl.setC_id(Long.parseLong("0"));
					income_dtl.setIncome_item("退款");
					income_dtl.setIncome_type("支出");
					income_dtl.setOperate_dt(new Date());
					income_dtl.setOrders_id(pushReturns.getId());
					income_dtl.setPrice(pushReturns.getReturn_price());
					income_dtl.setSeller_id(pushReturns.getSeller_id());
					iIncome_dtlMapper.addincome_dtl(income_dtl);
					
					//商家信息
					Seller seller = iSellerMapper.selectsellerById(pushReturns.getSeller_id()+"");
					Float price=Float.parseFloat(pushReturns.getReturn_price());
					Seller tempSeller = new Seller();
					tempSeller.setId(seller.getId());
					tempSeller.setAll_income((Float.parseFloat(seller.getAll_income())-price)+"");
					tempSeller.setNo_clear_income((Float.parseFloat(seller.getNo_clear_income())-price)+"");
					iSellerMapper.updateseller(tempSeller);
					//all_income 总收入
					//no_clear_income  冻结收入
					//cash 已提现金额
					//can_cash 可提现金额
					//cashing 提现中金额
					//商品数量++
					Order_item oi=iOrder_itemMapper.selectorder_itemById(pushReturns.getOrder_item_ids());
					Goods goods=iGoodsMapper.selectgoodsById(oi.getGoods_id()+"");
					if(goods!=null){
						Goods tempGoods= new Goods();
						tempGoods.setId(goods.getId());
						tempGoods.setNew_stock_amount(goods.getNew_stock_amount()+oi.getAmount());
						tempGoods.setSt_stock_amount(goods.getSt_stock_amount()+oi.getAmount());
						iGoodsMapper.updategoods(tempGoods);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			///所有退款完成后订单设置为完成
			Map paramMap=new HashMap();
			paramMap.put("orders_id",pushReturns.getOrders_id()+""); 
			int returnNum = iReturnsMapper.selectCountreturnsByParam(paramMap);
			paramMap.put("status","退款成功");
			int completeNum = iReturnsMapper.selectCountreturnsByParam(paramMap);
			if(returnNum==completeNum){
				Orders orders = new Orders();
				orders.setId(pushReturns.getOrders_id());
				orders.setStatus("已关闭");
				orders.setIs_close("Y");
				orders.setComplete_dt(new Date());
				iOrdersMapper.updateorders(orders);
			}
			
		}
		
		return result;
	}

	/**
	* 添加 
	* @return
	*/ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public  int addReturns(Returns returns){
		int result=0;
		
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String now = sdf.format(new Date());
		
	
		String codeNumber="0000001";
		Map paramMap=new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("type","returns");  
		paramMap.put("up_dt",now); 
		List<Order_num> onList=iOrder_numMapper.selectorder_numByParam(paramMap);
		Order_num order_num= new Order_num();
		order_num.setNum(codeNumber);
		order_num.setType("returns");
		if(onList.size()>0){
			String cnum=onList.get(0).getNum();
			codeNumber=String.format("%07d", Integer.parseInt(cnum) + 1);
			order_num.setNum(codeNumber);
			order_num.setId(onList.get(0).getId());
			
		}
		
		//编号
		returns.setReturn_code(now.substring(2,now.length())+"2"+codeNumber);
		result= iReturnsMapper.addreturns(returns);
		if(result>0){
			//更新编号
			if(codeNumber.equals("0000001")){
				iOrder_numMapper.addorder_num(order_num);
			}
			else{
				iOrder_numMapper.updateorder_num(order_num);
			}
			

			//推送
			PushUtils.push("s_"+returns.getSeller_id(), returns.getId()+"", "1","return","您有新退货申请，请及时处理");
		}
		
		
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdReturns(List<Returns> list){
		return iReturnsMapper.muladdreturns(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteReturns(String id){
		return iReturnsMapper.deletereturns(id);
	}

}

