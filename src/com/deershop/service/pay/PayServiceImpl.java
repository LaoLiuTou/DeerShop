package com.deershop.service.pay;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.deershop.dao.order_num.IOrder_numMapper;
import com.deershop.dao.orders.IOrdersMapper;
import com.deershop.dao.pay.IPayMapper;
import com.deershop.model.order_num.Order_num;
import com.deershop.model.orders.Orders;
import com.deershop.model.pay.Pay;
import com.deershop.service.orders.IOrdersService;
public class PayServiceImpl  implements IPayService {

	@Autowired
	private IPayMapper iPayMapper;
	@Autowired
	private IOrder_numMapper iOrder_numMapper;
	@Autowired
	private IOrdersMapper iOrdersMapper;
	@Autowired
	private IOrdersService iOrdersService;
	/**
	* 通过id选取
	* @return
	*/
	public Pay selectPayById(String id){
		return iPayMapper.selectpayById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Pay> selectPayByParam(Map paramMap){ 
		return iPayMapper.selectpayByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountPayByParam(Map paramMap){ 
		return iPayMapper.selectCountpayByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updatePay(Pay pay){
		return iPayMapper.updatepay(pay);
	}
	@Transactional
	public  int updatePayByPayId(Pay pay){
		int result = 0;
		result=iPayMapper.updatePayByPayId(pay);
		if(result>0){
			List<Pay> payList=iPayMapper.selectpayByPayId(pay.getPay_id());
			for(Pay temp:payList){
				Orders orders=iOrdersService.selectOrdersById(temp.getOrder_ids());
				if(orders!=null){
					if(orders.getIs_pay()!=null&&orders.getIs_pay().equals("Y")){
						
					}
					else{
						Orders o=new Orders();
						o.setPay_dt(new Date());
						o.setPay_type("微信");
						o.setIs_pay("Y");
						o.setStatus("已支付");
						o.setId(Long.parseLong(temp.getOrder_ids()));
						//iOrdersMapper.updateorders(o);
						iOrdersService.updateOrders(o);
					}
					
					
				}
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
	public  int addPay(Pay pay){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		int result=0;
		String codeNumber="00000001";
		Map paramMap=new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("type","pay");  
		List<Order_num> onList=iOrder_numMapper.selectorder_numByParam(paramMap);
		Order_num order_num= new Order_num();
		order_num.setNum(codeNumber);
		order_num.setType("pay");
		if(onList.size()>0){
			String cnum=onList.get(0).getNum();
			codeNumber=String.format("%08d", Integer.parseInt(cnum) + 1);
			order_num.setNum(codeNumber);
			order_num.setId(onList.get(0).getId());
		}
		
		//付款编号
		pay.setPay_id(sdf.format(new Date())+(new Date().getTime()/1000)*(Integer.parseInt(codeNumber)%10));
		 
		if(pay.getOrder_ids()!=null){
			String[] orderids=pay.getOrder_ids().split("\\,");
			for(String id:orderids){
				pay.setOrder_ids(id);
				result+=iPayMapper.addpay(pay);
			}
		}
		
		if(result>0){
			//更新编号
			if(codeNumber.equals("00000001")){
				iOrder_numMapper.addorder_num(order_num);
			}
			else{
				iOrder_numMapper.updateorder_num(order_num);
			}
		}
		
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdPay(List<Pay> list){
		return iPayMapper.muladdpay(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deletePay(String id){
		return iPayMapper.deletepay(id);
	}

}

