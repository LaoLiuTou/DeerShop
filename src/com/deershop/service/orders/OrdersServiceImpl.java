package com.deershop.service.orders;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.deershop.controller.PushUtils;
import com.deershop.dao.cart.ICartMapper;
import com.deershop.dao.goods.IGoodsMapper;
import com.deershop.dao.income_dtl.IIncome_dtlMapper;
import com.deershop.dao.order_item.IOrder_itemMapper;
import com.deershop.dao.order_num.IOrder_numMapper;
import com.deershop.dao.orders.IOrdersMapper;
import com.deershop.dao.seller.ISellerMapper;
import com.deershop.model.cart.Cart;
import com.deershop.model.goods.Goods;
import com.deershop.model.income_dtl.Income_dtl;
import com.deershop.model.order_item.Order_item;
import com.deershop.model.order_num.Order_num;
import com.deershop.model.orders.Orders;
import com.deershop.model.seller.Seller;
import com.deershop.service.order_item.IOrder_itemService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
public class OrdersServiceImpl  implements IOrdersService {

	@Autowired
	private IOrdersMapper iOrdersMapper;
	@Autowired
	private IOrder_itemMapper iOrder_itemMapper;
	@Autowired
	private IOrder_itemService iOrder_itemService;
	@Autowired
	private IOrder_numMapper iOrder_numMapper;
	@Autowired
	private ICartMapper iCartMapper;
	@Autowired
	private IIncome_dtlMapper iIncome_dtlMapper;
	@Autowired
	private ISellerMapper iSellerMapper;
	@Autowired
	private IGoodsMapper iGoodsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Orders selectOrdersById(String id){
		return iOrdersMapper.selectordersById(id);
	}
	public Orders selectordersByIdApp(String id){
		return iOrdersMapper.selectordersByIdApp(id);
	}
	public Orders selectCdOrdersById(String id){
		return iOrdersMapper.selectcdordersById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Orders> selectOrdersByParam(Map paramMap){ 
		return iOrdersMapper.selectordersByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountOrdersByParam(Map paramMap){ 
		return iOrdersMapper.selectCountordersByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public  int updateOrders(Orders orders){
		int result=0;
		if(orders.getIs_confirm()!=null&&orders.getIs_confirm().equals("Y")){
			orders.setComplete_dt(new Date());
		}
		else if(orders.getIs_close()!=null&&orders.getIs_close().equals("Y")){
			orders.setComplete_dt(new Date());
		}
		else if(orders.getIs_cancel()!=null&&orders.getIs_cancel().equals("Y")){
			orders.setComplete_dt(new Date());
		}
		result=iOrdersMapper.updateorders(orders);
		if(result>0){

			
			try {
				//推送
				String message="";
				Orders pushOrders=iOrdersMapper.selectordersById(orders.getId()+"");
				if(orders.getIs_pay()!=null&&orders.getIs_pay().equals("Y")){
					//资金收入支出
					Income_dtl income_dtl=new Income_dtl();
					income_dtl.setC_id(Long.parseLong("0"));
					income_dtl.setIncome_item("订单收入");
					income_dtl.setIncome_type("收入");
					income_dtl.setOperate_dt(new Date());
					income_dtl.setOrders_id(orders.getId());
					income_dtl.setPrice(pushOrders.getAll_price());
					income_dtl.setSeller_id(pushOrders.getSeller_id());
					iIncome_dtlMapper.addincome_dtl(income_dtl);
					//商家信息
					Seller seller = iSellerMapper.selectsellerById(pushOrders.getSeller_id()+"");
					Float price=Float.parseFloat(pushOrders.getAll_price());
					Seller tempSeller = new Seller();
					tempSeller.setId(seller.getId());
					tempSeller.setAll_income((Float.parseFloat(seller.getAll_income())+price)+"");
					tempSeller.setNo_clear_income((Float.parseFloat(seller.getNo_clear_income())+price)+"");
					iSellerMapper.updateseller(tempSeller);
					//all_income 总收入
					//no_clear_income  冻结收入
					//cash 已提现金额
					//can_cash 可提现金额
					//cashing 提现中金额
					message="您有已付款的订单，请及时处理！";
					PushUtils.push("s_"+pushOrders.getSeller_id(), pushOrders.getId()+"","1","order", message);
					
				}
				else if(orders.getIs_close()!=null&&orders.getIs_close().equals("Y")){
					message="您的订单("+pushOrders.getOrder_num()+")已关闭！";
					PushUtils.push("s_"+pushOrders.getSeller_id(), pushOrders.getId()+"", "1","order",message);
					PushUtils.push("b_"+pushOrders.getBuyer_id(), pushOrders.getId()+"","2","order", message);
				}
				else if(orders.getIs_cancel()!=null&&orders.getIs_cancel().equals("Y")){
					message="您的订单("+pushOrders.getOrder_num()+")已取消！";
					PushUtils.push("s_"+pushOrders.getSeller_id(), pushOrders.getId()+"", "1","order",message);
					PushUtils.push("b_"+pushOrders.getBuyer_id(), pushOrders.getId()+"", "2","order",message);
				}
				
				else if(orders.getIs_deliver()!=null&&orders.getIs_deliver().equals("Y")){
					message="您在"+pushOrders.getShop_name()+"的订单已发货！";
					PushUtils.push("b_"+pushOrders.getBuyer_id(), pushOrders.getId()+"","2","order", message);
				}
				else if(orders.getIs_confirm()!=null&&orders.getIs_confirm().equals("Y")){
					message="您的订单("+pushOrders.getOrder_num()+")已完成！";
					//商家信息
					Seller seller = iSellerMapper.selectsellerById(pushOrders.getSeller_id()+"");
					Float price=Float.parseFloat(pushOrders.getAll_price());
					Seller tempSeller = new Seller();
					tempSeller.setId(seller.getId());
					tempSeller.setNo_clear_income((Float.parseFloat(seller.getNo_clear_income())-price)+"");
					tempSeller.setCan_cash((Float.parseFloat(seller.getCan_cash())+price)+"");
					iSellerMapper.updateseller(tempSeller);
					//all_income 总收入
					//no_clear_income  冻结收入
					//cash 已提现金额
					//can_cash 可提现金额
					//cashing 提现中金额
					//销量
					Map paramMap=new HashMap();
					paramMap.put("orders_id",pushOrders.getId()); 
					int number = iOrder_itemMapper.selectCountorder_itemByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",number); 
					List<Order_item> listOi=iOrder_itemMapper.selectorder_itemByParam(paramMap);
					for(Order_item oi:listOi){
						Goods goods=iGoodsMapper.selectgoodsById(oi.getGoods_id()+"");
						if(goods!=null){
							Goods tempGoods= new Goods();
							tempGoods.setId(goods.getId());
							tempGoods.setSales(goods.getSales()+oi.getAmount());
							iGoodsMapper.updategoods(tempGoods);
						}
					}
					PushUtils.push("s_"+pushOrders.getSeller_id(), pushOrders.getId()+"","1","order", message);
					PushUtils.push("b_"+pushOrders.getBuyer_id(), pushOrders.getId()+"","2","order", message);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return result;
	}
	@Transactional
	public  int updateByOrdernum(Orders orders){
		return iOrdersMapper.updateByOrdernum(orders);
	}

	/**
	* 添加 
	* @return
	*/ 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public  int addOrders(Orders orders,String order_items){
		int result =0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String now = sdf.format(new Date());
			String codeNumber="00000001";
			Map paramMap=new HashMap();
			paramMap.put("fromPage",0);
			paramMap.put("toPage",1); 
			paramMap.put("type","order");  
			List<Order_num> onList=iOrder_numMapper.selectorder_numByParam(paramMap);
			Order_num order_num= new Order_num();
			order_num.setNum(codeNumber);
			order_num.setType("order");
			if(onList.size()>0){
				String cnum=onList.get(0).getNum();
				codeNumber=String.format("%08d", Integer.parseInt(cnum) + 1);
				order_num.setNum(codeNumber);
				order_num.setId(onList.get(0).getId());
			}
			
			//订单编号
			orders.setOrder_num(now.substring(2,now.length())+codeNumber);
			result = iOrdersMapper.addorders(orders);
			if(result>0){
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Order_item.class);
				List<Order_item> list = (List<Order_item>)objectMapper.readValue(order_items, javaType);
				for(int index=0;index<list.size();index++){
					list.get(index).setOrders_id(orders.getId());
				}
				iOrder_itemService.muladdOrder_item(list);
				
				//更新编号
				if(codeNumber.equals("00000001")){
					iOrder_numMapper.addorder_num(order_num);
				}
				else{
					iOrder_numMapper.updateorder_num(order_num);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public  int addOrders(Orders orders,List<Order_item> order_items){
		int result =0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String now = sdf.format(new Date());
			String codeNumber="00000001";
			Map paramMap=new HashMap();
			paramMap.put("fromPage",0);
			paramMap.put("toPage",1); 
			paramMap.put("type","order"); 
			List<Order_num> onList=iOrder_numMapper.selectorder_numByParam(paramMap);
			Order_num order_num= new Order_num();
			order_num.setNum(codeNumber);
			if(onList.size()>0){
				String cnum=onList.get(0).getNum();
				codeNumber=String.format("%08d", Integer.parseInt(cnum) + 1);
				order_num.setNum(codeNumber);
				order_num.setId(onList.get(0).getId());
			}
			
			//订单编号
			orders.setOrder_num(now.substring(2,now.length())+codeNumber);
			orders.setCreate_dt(new Date());
			result = iOrdersMapper.addorders(orders);
			if(result>0){
				for(int index=0;index<order_items.size();index++){
					order_items.get(index).setOrders_id(orders.getId());
					
					//删除购物车
					Cart c= new Cart();
					c.setBuyer_id(orders.getBuyer_id());
					c.setGoods_id(order_items.get(index).getGoods_id());
					iCartMapper.deletecartByParam(c);
					
				}
				//iOrder_itemMapper.muladdorder_item(order_items);
				iOrder_itemService.muladdOrder_item(order_items);
				
				//更新编号
				if(codeNumber.equals("00000001")){
					iOrder_numMapper.addorder_num(order_num);
				}
				else{
					iOrder_numMapper.updateorder_num(order_num);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdOrders(List<Orders> list){
		return iOrdersMapper.muladdorders(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteOrders(String id){
		return iOrdersMapper.deleteorders(id);
	}

}

