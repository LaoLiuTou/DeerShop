package com.deershop.service.send;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.deershop.dao.orders.IOrdersMapper;
import com.deershop.dao.send.ISendMapper;
import com.deershop.model.orders.Orders;
import com.deershop.model.send.Send;
public class SendServiceImpl  implements ISendService {

	@Autowired
	private ISendMapper iSendMapper;
	@Autowired
	private IOrdersMapper iOrdersMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Send selectSendById(String id){
		return iSendMapper.selectsendById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Send> selectSendByParam(Map paramMap){ 
		return iSendMapper.selectsendByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountSendByParam(Map paramMap){ 
		return iSendMapper.selectCountsendByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateSend(Send send){
		return iSendMapper.updatesend(send);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addSend(Send send){
		int result=0;
		send.setSend_dt(new Date());
		send.setStatus("1");
		result=iSendMapper.addsend(send);
		if(result>0){
			Orders temp =new  Orders();
			temp.setId(send.getOrders_id());
			temp.setIs_deliver("Y");
			temp.setStatus("已发货");
			iOrdersMapper.updateorders(temp);
		}
		
		return result;
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdSend(List<Send> list){
		return iSendMapper.muladdsend(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteSend(String id){
		return iSendMapper.deletesend(id);
	}

}

