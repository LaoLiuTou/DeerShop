package com.deershop.service.cash_apply;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.deershop.controller.PushUtils;
import com.deershop.dao.cash_apply.ICash_applyMapper;
import com.deershop.dao.income_dtl.IIncome_dtlMapper;
import com.deershop.dao.lov.ILovMapper;
import com.deershop.dao.order_num.IOrder_numMapper;
import com.deershop.dao.seller.ISellerMapper;
import com.deershop.model.cash_apply.Cash_apply;
import com.deershop.model.income_dtl.Income_dtl;
import com.deershop.model.lov.Lov;
import com.deershop.model.order_item.Order_item;
import com.deershop.model.order_num.Order_num;
import com.deershop.model.seller.Seller;
import com.deershop.utils.Base64;
import com.deershop.utils.HttpRequest;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Cash_applyServiceImpl  implements ICash_applyService {

	@Autowired
	private ICash_applyMapper iCash_applyMapper;
	@Autowired
	private IOrder_numMapper iOrder_numMapper;
	@Autowired
	private ILovMapper iLovMapper;
	@Autowired
	private IIncome_dtlMapper iIncome_dtlMapper;
	@Autowired
	private ISellerMapper iSellerMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Cash_apply selectCash_applyById(String id){
		return iCash_applyMapper.selectcash_applyById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Cash_apply> selectCash_applyByParam(Map paramMap){ 
		return iCash_applyMapper.selectcash_applyByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCash_applyByParam(Map paramMap){ 
		return iCash_applyMapper.selectCountcash_applyByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateCash_apply(Cash_apply cash_apply){
		int result=0;
		result= iCash_applyMapper.updatecash_apply(cash_apply);
		return result;
	}
	@Transactional
	public  int offlinePay(Cash_apply cash_apply){
		int result=0;
		cash_apply.setDeal_date(new Date());
		cash_apply.setCash_ok_dt(new Date());
		cash_apply.setCash_status("已处理");
		result= iCash_applyMapper.updatecash_apply(cash_apply);
		if(result>0){
			Cash_apply ca=iCash_applyMapper.selectcash_applyById(cash_apply.getId()+"");
			//商家信息
			Seller seller = iSellerMapper.selectsellerById(ca.getSeller_id()+"");
			Float  cash = seller.getCash()!=null?Float.parseFloat(seller.getCash()):0;
			Float  cashing = seller.getCashing()!=null?Float.parseFloat(seller.getCashing()):0;
			Float price=ca.getPrice()!=null?Float.parseFloat(ca.getPrice()):0; 
			Seller tempSeller = new Seller();
			tempSeller.setId(seller.getId());
			tempSeller.setCash((cash+price)+"");
			tempSeller.setCashing((cashing-price)+"");
			iSellerMapper.updateseller(tempSeller);
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public  String payToCard(Cash_apply cash_apply){
		Logger logger = Logger.getLogger("DeerShopLogger");
		String result="";
		try {
			Cash_apply temp=iCash_applyMapper.selectcash_applyById(cash_apply.getId()+"");
			ObjectMapper objectMapper = new ObjectMapper();
			//银行卡信息
			Map<String,String> cashInfoMap = objectMapper.readValue(temp.getCash_info(), Map.class);
			
			Properties properties = new Properties();
			String base = PushUtils.class.getResource("/")
					.getPath();
			properties.load(new FileInputStream(base
						+ "jdbc/jdbc.properties"));
			String payUrl=properties.getProperty("payCardUrl").trim();
			
			int price = (int)Float.parseFloat(temp.getCash_price())*100;
			Map<String,String> map=new HashMap<String,String>();
			map.put("cardnum", cashInfoMap.get("card_num"));
			map.put("owner", cashInfoMap.get("card_name"));
			map.put("bank", cashInfoMap.get("bank"));
			map.put("partnertradeno", temp.getCash_code());
			map.put("amount", price+"");
			//支付结果
			String payResult = HttpRequest.postMap(payUrl, null,map);
			logger.info("记录("+cash_apply.getId()+")支付结果:"+payResult);
			Map<String,String> resultMap = objectMapper.readValue(payResult, Map.class);
			if(resultMap.size()>0){
				if(resultMap.get("return_code").equals("SUCCESS")&&
						resultMap.get("result_code").equals("SUCCESS")){
					cash_apply.setDeal_date(new Date());
					cash_apply.setCash_ok_dt(new Date());
					cash_apply.setCash_status("已处理");
					iCash_applyMapper.updatecash_apply(cash_apply);
					result="提现成功！";
					
					Cash_apply ca=iCash_applyMapper.selectcash_applyById(cash_apply.getId()+"");
					//商家信息
					Seller seller = iSellerMapper.selectsellerById(ca.getSeller_id()+"");
					Float  cash = seller.getCash()!=null?Float.parseFloat(seller.getCash()):0;
					Float  cashing = seller.getCashing()!=null?Float.parseFloat(seller.getCashing()):0;
					Float cash_price=ca.getPrice()!=null?Float.parseFloat(ca.getPrice()):0; 
					Seller tempSeller = new Seller();
					tempSeller.setId(seller.getId());
					tempSeller.setCash((cash+cash_price)+"");
					tempSeller.setCashing((cashing-cash_price)+"");
					iSellerMapper.updateseller(tempSeller);
				}
				else{
					result=resultMap.get("return_msg");
				}
			}
			else{
				result="提现失败！";
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		return result;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public  String payToWechat(Cash_apply cash_apply){
		Logger logger = Logger.getLogger("DeerShopLogger");
		String result="";
		try {
			Cash_apply temp=iCash_applyMapper.selectcash_applyById(cash_apply.getId()+"");
			ObjectMapper objectMapper = new ObjectMapper();
			//银行卡信息
			Map<String,String> cashInfoMap = objectMapper.readValue(temp.getCash_info(), Map.class);
			
			Properties properties = new Properties();
			String base = PushUtils.class.getResource("/")
					.getPath();
			properties.load(new FileInputStream(base
					+ "jdbc/jdbc.properties"));
			String payUrl=properties.getProperty("payWechatUrl").trim();
			
			int price = (int)Float.parseFloat(temp.getCash_price())*100;
			Map<String,String> map=new HashMap<String,String>();
			map.put("openid", cashInfoMap.get("open_id"));
			map.put("partnertradeno", temp.getCash_code());
			map.put("amount", price+"");
			 
			//支付结果
			String payResult = HttpRequest.postMap(payUrl, null,map);
			logger.info("记录("+cash_apply.getId()+")支付结果:"+payResult);
			Map<String,String> resultMap = objectMapper.readValue(payResult, Map.class);
			if(resultMap.size()>0){
				if(resultMap.get("return_code").equals("SUCCESS")&&
						resultMap.get("result_code").equals("SUCCESS")){
					cash_apply.setDeal_date(new Date());
					cash_apply.setCash_ok_dt(new Date());
					cash_apply.setCash_status("已处理");
					iCash_applyMapper.updatecash_apply(cash_apply);
					result="提现成功！";
					Cash_apply ca=iCash_applyMapper.selectcash_applyById(cash_apply.getId()+"");
					//商家信息
					Seller seller = iSellerMapper.selectsellerById(ca.getSeller_id()+"");
					Float  cash = seller.getCash()!=null?Float.parseFloat(seller.getCash()):0;
					Float  cashing = seller.getCashing()!=null?Float.parseFloat(seller.getCashing()):0;
					Float cash_price=ca.getPrice()!=null?Float.parseFloat(ca.getPrice()):0; 
					Seller tempSeller = new Seller();
					tempSeller.setId(seller.getId());
					tempSeller.setCash((cash+cash_price)+"");
					tempSeller.setCashing((cashing-cash_price)+"");
					iSellerMapper.updateseller(tempSeller);
				}
				else{
					result=resultMap.get("return_msg");
				}
			}
			else{
				result="提现失败！";
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		return result;
	}

	/**
	* 添加 
	* @return
	*/ 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public  int addCash_apply(Cash_apply cash_apply){
		int result =0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String now = sdf.format(new Date());
		//商家信息
		Seller seller = iSellerMapper.selectsellerById(cash_apply.getSeller_id()+"");
		Float  can_cash = seller.getCan_cash()!=null?Float.parseFloat(seller.getCan_cash()):0;
		Float price=cash_apply.getPrice()!=null?Float.parseFloat(cash_apply.getPrice()):0; 
		//提现金额错误
		if(price<1){
			result=-2;
			return result;	
		}
		//提现金额>可提现
		if(can_cash<price){
			result=-3;
			return result;	
		}
		String codeNumber="00000001";
		Map paramMap=new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("type","cash");  
		paramMap.put("up_dt",now); 
		List<Order_num> onList=iOrder_numMapper.selectorder_numByParam(paramMap);
		Order_num order_num= new Order_num();
		order_num.setNum(codeNumber);
		order_num.setType("cash");
		if(onList.size()>0){
			String cnum=onList.get(0).getNum();
			codeNumber=String.format("%08d", Integer.parseInt(cnum) + 1);
			order_num.setNum(codeNumber);
			order_num.setId(onList.get(0).getId());
		}
		cash_apply.setCash_st_dt(new Date());
		//编号
		cash_apply.setCash_code(now+codeNumber);
		cash_apply.setCash_status("待处理");
		//提现费率
		paramMap=new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("lov_name","提现费率"); 
		List<Lov> temp=iLovMapper.selectlovByParam(paramMap);
		if(temp.size()>0){
			if(temp.get(0).getImg()!=null){
				
				DecimalFormat decimalFormat=new DecimalFormat("##0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
				String cash_service=decimalFormat.format(Float.parseFloat(cash_apply.getPrice())*Float.parseFloat(temp.get(0).getImg()));
				String cash_price=decimalFormat.format((Float.parseFloat(cash_apply.getPrice())-Float.parseFloat(cash_apply.getPrice())*Float.parseFloat(temp.get(0).getImg())));
				if(!cash_apply.getCash_rate().equals(temp.get(0).getImg())||
					!cash_apply.getCash_service().equals(cash_service)||
					!cash_apply.getCash_price().equals(cash_price)){
					result=-1;
					return result;
				}
				
			}
			else{
				result=-1;
				return result;
			}
			
		}
		result=iCash_applyMapper.addcash_apply(cash_apply);
		if(result>0){
			//更新编号
			if(codeNumber.equals("00000001")){
				iOrder_numMapper.addorder_num(order_num);
			}
			else{
				iOrder_numMapper.updateorder_num(order_num);
			}
			//保存到收支明细
			Income_dtl income_dtl=new Income_dtl();
			income_dtl.setC_id(Long.parseLong("0"));
			income_dtl.setIncome_item("提现");
			income_dtl.setIncome_type("支出");
			income_dtl.setOperate_dt(new Date());
			income_dtl.setOrders_id(cash_apply.getId());
			income_dtl.setPrice(cash_apply.getPrice());
			income_dtl.setSeller_id(cash_apply.getSeller_id());
			iIncome_dtlMapper.addincome_dtl(income_dtl);
			
			Seller tempSeller = new Seller();
			tempSeller.setId(seller.getId());
			//tempSeller.setCash((Float.parseFloat(seller.getCash())+price)+"");
			tempSeller.setCan_cash((Float.parseFloat(seller.getCan_cash())-price)+"");
			tempSeller.setCashing((Float.parseFloat(seller.getCashing())+price)+"");
			iSellerMapper.updateseller(tempSeller);
			//all_income 总收入
			//no_clear_income  冻结收入
			//cash 已提现金额
			//can_cash 可提现金额
			//cashing 提现中金额

		}
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdCash_apply(List<Cash_apply> list){
		return iCash_applyMapper.muladdcash_apply(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteCash_apply(String id){
		return iCash_applyMapper.deletecash_apply(id);
	}

}

