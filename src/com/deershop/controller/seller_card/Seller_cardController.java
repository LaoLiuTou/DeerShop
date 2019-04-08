package com.deershop.controller.seller_card;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.deershop.service.seller_card.ISeller_cardService;
import com.deershop.utils.HttpRequest;
import com.deershop.controller.PushUtils;
import com.deershop.model.seller_card.Seller_card;
@Controller
public class Seller_cardController {
	@Autowired
	private ISeller_cardService iSeller_cardService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addSeller_card")
	@ResponseBody
	public Map add(Seller_card seller_card){
		Map resultMap=new HashMap();
		try {
			if(seller_card.getCard_num()!=null){
				Properties properties = new Properties();
				String base = Seller_cardController.class.getResource("/")
						.getPath();
				properties.load(new FileInputStream(base
							+ "jdbc/jdbc.properties"));
				String cardValidateUrl=properties.getProperty("cardValidateUrl").trim();
				cardValidateUrl=cardValidateUrl.replace("cardNumber", seller_card.getCard_num());
				String cardResult=HttpRequest.postMap(cardValidateUrl, null,null);
				ObjectMapper objectMapper = new ObjectMapper();
				Map cardMap=objectMapper.readValue(cardResult, Map.class);
				if(cardMap!=null&&(cardMap.get("validated")+"").equals("true")){
					iSeller_cardService.addSeller_card(seller_card);
					resultMap.put("status", "0");
					resultMap.put("msg", seller_card.getId());
					logger.info("新建成功，主键："+seller_card.getId());
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "卡号输入不正确！");
				}
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "卡号不能为空！");
			}
			
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdSeller_card")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Seller_card seller_card){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Seller_card.class);
			List<Seller_card> list = (List<Seller_card>)objectMapper.readValue(data, javaType);
			iSeller_cardService.muladdSeller_card(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+seller_card.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteSeller_card")
	@ResponseBody
	public Map delete(Seller_card seller_card){
		Map resultMap=new HashMap();
		try {
			if(seller_card.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iSeller_cardService.deleteSeller_card(seller_card.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+seller_card.getId());
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
	@RequestMapping("/selectSeller_card")
	@ResponseBody
	public Map select(Seller_card seller_card){
		Map resultMap=new HashMap();
		try {
			if(seller_card.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Seller_card resultSelect=iSeller_cardService.selectSeller_cardById(seller_card.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", resultSelect);
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
	@RequestMapping("/updateSeller_card")
	@ResponseBody
	public Map update(Seller_card seller_card){
		Map resultMap=new HashMap();
		try {
			if(seller_card.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iSeller_cardService.updateSeller_card(seller_card);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+seller_card.getId());
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
	@RequestMapping("/listSeller_card")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Seller_card seller_card)
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
				paramMap.put("id",seller_card.getId());
				paramMap.put("c_id",seller_card.getC_id());
				paramMap.put("seller_id",seller_card.getSeller_id());
				paramMap.put("card_num",seller_card.getCard_num());
				paramMap.put("bank",seller_card.getBank());
				paramMap.put("card_name",seller_card.getCard_name());
				paramMap.put("status",seller_card.getStatus());
				String c_dtFrom=request.getParameter("c_dtFrom");
				String c_dtTo=request.getParameter("c_dtTo");
				if(c_dtFrom!=null&&!c_dtFrom.equals(""))
				paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
				if(c_dtTo!=null&&!c_dtTo.equals(""))
				paramMap.put("c_dtTo", sdf.parse(c_dtTo));
				String up_dtFrom=request.getParameter("up_dtFrom");
				String up_dtTo=request.getParameter("up_dtTo");
				if(up_dtFrom!=null&&!up_dtFrom.equals(""))
				paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
				if(up_dtTo!=null&&!up_dtTo.equals(""))
				paramMap.put("up_dtTo", sdf.parse(up_dtTo));
				List<Seller_card> list=iSeller_cardService.selectSeller_cardByParam(paramMap);
				int totalnumber=iSeller_cardService.selectCountSeller_cardByParam(paramMap);
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
}
