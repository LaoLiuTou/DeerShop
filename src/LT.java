import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deershop.model.order_num.Order_num;
import com.deershop.utils.Base64;
import com.deershop.utils.MD5Encryption;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class LT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String codeNumber="13100000001";
		 
		 
			//String cnum="00000005";
			//codeNumber=String.format("%08d", Integer.parseInt(cnum) + 1);
			/*String localpwdStr=MD5Encryption.getEncryption(codeNumber).substring(0, 16)+
					"e10adc3949ba59abbe56e057f20f883e".substring(0,16);*/
			//075d147f214b5b64e10adc3949ba59ab
			//e5b546a8f4ea5a32e10adc3949ba59ab
			//e5b546a8f4ea5a32e10adc3949ba59ab
		try {
			/*Map<String,Object> map=new HashMap<String,Object>();
			map.put("userid", "system");
			map.put("friendid", "1");
			map.put("username", "订单消息");
			 
			
			Map<String,String> contentMap=new HashMap<String,String>();
			contentMap.put("id", "2");
			contentMap.put("type", "order");
			contentMap.put("content", "您有新的订单！");
			contentMap.put("messageid","3");
			ObjectMapper objectMapper = new ObjectMapper();
			
			map.put("content", contentMap);
			String jsonStr =  objectMapper.writeValueAsString(map);
			 System.out.println(jsonStr);*/
			
			 Long a = Long.parseLong("5");
			 Long b=Long.parseLong("1");
			 System.out.println(a-b);
			
			//String friendIds="s_1";
			//System.out.println(friendIds.split("_")[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=6210810940001378989&cardBinCheck=true
}
