package com.deershop.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deershop.model.seller.Seller;
import com.deershop.model.system_message.System_message;
import com.deershop.service.seller.ISellerService;
import com.deershop.service.system_message.ISystem_messageService;
import com.deershop.utils.Base64;
import com.deershop.utils.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@Component 
public class PushUtils {
	 
    private static ISystem_messageService iSystem_messageService;
    private static ISellerService iSellerService;
    
    @Autowired
    public PushUtils(ISystem_messageService iSystem_messageService,ISellerService iSellerService) {
    	PushUtils.iSystem_messageService = iSystem_messageService;
    	PushUtils.iSellerService = iSellerService;
    }
	/**
	 * 
	 * @param friendIds 接收人id
	 * @param id 记录id
	 * @param type 接收人type
	 * @param messageType  订单、退款
	 * @param content 内容
	 */
	public static void push(String friendIds,String id,String type,String messageType,String content){
		Logger logger = Logger.getLogger("DeerShopLogger");
		try {
			boolean sendFlag=true;
			String [] userIds=friendIds.split("_");
			System_message sm= new System_message();
			sm.setMessage_content(content);
			sm.setMessage_type(messageType);
			sm.setSendee_id(Long.parseLong(userIds[1]));
			sm.setSendee_type(type);
			sm.setStatus("1");
			String title="";
			if(messageType.equals("order")){
				title="订单消息";
			}
			else if(messageType.equals("return")){
				title="退款消息";
			}
			else if(messageType.equals("cash")){
				title="提现消息";
			}
			sm.setTitle(title);
			sm.setOrder_id(Long.parseLong(id));
			iSystem_messageService.addSystem_message(sm);
			
			//查询用户是否接收通知
			if(userIds[0].equals("s")){
				Seller seller = iSellerService.selectSellerById(userIds[1]);
				if(seller!=null){
					String is_order_notice= seller.getIs_order_notice();
					if(is_order_notice!=null&&is_order_notice.equals("0")){
						sendFlag=false;
					}
				}
			}
			
			if(sendFlag){
				Properties properties = new Properties();
				String base = PushUtils.class.getResource("/")
						.getPath();
				properties.load(new FileInputStream(base
							+ "jdbc/jdbc.properties"));
				String pushUrl=properties.getProperty("pushUrl").trim();
				Map<String,String> map=new HashMap<String,String>();
				map.put("userid", "system");
				map.put("friendid", friendIds);
				map.put("username", title);
				map.put("type", "0");
				map.put("key", new Date().getTime()+(int)((Math.random()*9+1)*100000)+"");
				map.put("operate", "0");
				map.put("delay", "100");
				
				Map<String,String> contentMap=new HashMap<String,String>();
				contentMap.put("id", id);
				contentMap.put("type", type);
				contentMap.put("content", content);
				contentMap.put("messageid", sm.getId()+"");
				ObjectMapper objectMapper = new ObjectMapper();
				String jsonStr = Base64.getBase64(objectMapper.writeValueAsString(contentMap));
				map.put("content", jsonStr);
				logger.info("Post请求:"+HttpRequest.postMap(pushUrl, null,map));
				logger.info("发送推送消息到用户："+friendIds);
			}
			
		} catch (Exception e) {
			logger.info("发送推送消息异常");
			e.printStackTrace();
		} 
	}

}
