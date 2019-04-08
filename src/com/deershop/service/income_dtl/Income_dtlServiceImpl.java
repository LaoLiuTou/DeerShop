package com.deershop.service.income_dtl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.deershop.dao.cash_apply.ICash_applyMapper;
import com.deershop.dao.income_dtl.IIncome_dtlMapper;
import com.deershop.dao.orders.IOrdersMapper;
import com.deershop.dao.returns.IReturnsMapper;
import com.deershop.model.cash_apply.Cash_apply;
import com.deershop.model.income_dtl.Income_dtl;
import com.deershop.model.orders.Orders;
import com.deershop.model.returns.Returns;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Income_dtlServiceImpl  implements IIncome_dtlService {

	@Autowired
	private IIncome_dtlMapper iIncome_dtlMapper;
	@Autowired
	private IOrdersMapper iOrdersMapper;
	@Autowired
	private IReturnsMapper iReturnsMapper;
	@Autowired
	private ICash_applyMapper iCash_applyMapper;
	/**
	* 通过id选取
	* @return
	*/
	@SuppressWarnings("unchecked")
	public Income_dtl selectIncome_dtlById(String id){
		Income_dtl income_dtl=iIncome_dtlMapper.selectincome_dtlById(id);
		if(income_dtl.getIncome_item()!=null&&income_dtl.getOrders_id()!=null&&income_dtl.getIncome_item().equals("订单收入")){
			Orders orders=iOrdersMapper.selectordersById(income_dtl.getOrders_id()+""); 
			if(orders!=null){
				income_dtl.setNum(orders.getOrder_num());
			}
		}
		else if(income_dtl.getIncome_item()!=null&&income_dtl.getOrders_id()!=null&&income_dtl.getIncome_item().equals("提现")){
			Cash_apply ca=iCash_applyMapper.selectcash_applyById(income_dtl.getOrders_id()+""); 
			if(ca!=null){
				if(ca.getCash_code()!=null){
					income_dtl.setNum(ca.getCash_code());
				}
				if(ca.getCash_info()!=null){
					try {
						ObjectMapper objectMapper = new ObjectMapper();
						Map<String,String> temp = (Map<String,String>)objectMapper.readValue(ca.getCash_info(), Map.class);
						String bankInfo=temp.get("bank");
						String cardNum=temp.get("card_num");
						if(cardNum!=null&&cardNum.length()>4){
							bankInfo=bankInfo+"("+cardNum.substring(cardNum.length()-4, cardNum.length())+")";
						}
						income_dtl.setBankinfo(bankInfo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				if(ca.getCash_status()!=null){
					income_dtl.setStatus(ca.getCash_status());
				}
				
			}
		}
		else if(income_dtl.getIncome_item()!=null&&income_dtl.getOrders_id()!=null&&income_dtl.getIncome_item().equals("退款")){
			Returns returns=iReturnsMapper.selectreturnsById(income_dtl.getOrders_id()+""); 
			if(returns!=null){
				income_dtl.setNum(returns.getReturn_code());
			}
		}
		return income_dtl;
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Income_dtl> selectIncome_dtlByParam(Map paramMap){ 
		List<Income_dtl> temp = iIncome_dtlMapper.selectincome_dtlByParam(paramMap);
		for(Income_dtl id:temp){
			if(id.getIncome_item()!=null&&id.getOrders_id()!=null&&id.getIncome_item().equals("订单收入")){
				Orders orders=iOrdersMapper.selectordersById(id.getOrders_id()+""); 
				if(orders!=null){
					id.setNum(orders.getOrder_num());
				}
			}
			else if(id.getIncome_item()!=null&&id.getOrders_id()!=null&&id.getIncome_item().equals("提现")){
				Cash_apply ca=iCash_applyMapper.selectcash_applyById(id.getOrders_id()+""); 
				if(ca!=null){
					if(ca.getCash_code()!=null){
						id.setNum(ca.getCash_code());
					}
					if(ca.getCash_info()!=null){
						try {
							ObjectMapper objectMapper = new ObjectMapper();
							Map<String,String> tempMap = (Map<String,String>)objectMapper.readValue(ca.getCash_info(), Map.class);
							String bankInfo=tempMap.get("bank");
							String cardNum=tempMap.get("card_num");
							if(cardNum!=null&&cardNum.length()>4){
								bankInfo=bankInfo+"("+cardNum.substring(cardNum.length()-4, cardNum.length())+")";
							}
							id.setBankinfo(bankInfo);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
					if(ca.getCash_status()!=null){
						id.setStatus(ca.getCash_status());
					}
				}
			}
			else if(id.getIncome_item()!=null&&id.getOrders_id()!=null&&id.getIncome_item().equals("退款")){
				Returns returns=iReturnsMapper.selectreturnsById(id.getOrders_id()+""); 
				if(returns!=null){
					id.setNum(returns.getReturn_code());
				}
			}
		}
	 
		return temp;
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountIncome_dtlByParam(Map paramMap){ 
		return iIncome_dtlMapper.selectCountincome_dtlByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateIncome_dtl(Income_dtl income_dtl){
		return iIncome_dtlMapper.updateincome_dtl(income_dtl);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addIncome_dtl(Income_dtl income_dtl){
		return iIncome_dtlMapper.addincome_dtl(income_dtl);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdIncome_dtl(List<Income_dtl> list){
		return iIncome_dtlMapper.muladdincome_dtl(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteIncome_dtl(String id){
		return iIncome_dtlMapper.deleteincome_dtl(id);
	}

}

