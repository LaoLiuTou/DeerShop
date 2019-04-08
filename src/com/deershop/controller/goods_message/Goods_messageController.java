package com.deershop.controller.goods_message;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.deershop.service.goods_message.IGoods_messageService;
import com.deershop.model.goods_message.Goods_message;
@Controller
public class Goods_messageController {
	@Autowired
	private IGoods_messageService iGoods_messageService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addGoods_message")
	@ResponseBody
	public Map add(Goods_message goods_message){
		Map resultMap=new HashMap();
		try {
			iGoods_messageService.addGoods_message(goods_message);
			resultMap.put("status", "0");
			resultMap.put("msg", goods_message.getId());
			logger.info("新建成功，主键："+goods_message.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdGoods_message")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Goods_message goods_message){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Goods_message.class);
			List<Goods_message> list = (List<Goods_message>)objectMapper.readValue(data, javaType);
			iGoods_messageService.muladdGoods_message(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+goods_message.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteGoods_message")
	@ResponseBody
	public Map delete(Goods_message goods_message){
		Map resultMap=new HashMap();
		try {
			if(goods_message.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iGoods_messageService.deleteGoods_message(goods_message.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+goods_message.getId());
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
	@RequestMapping("/selectGoods_message")
	@ResponseBody
	public Map select(Goods_message goods_message){
		Map resultMap=new HashMap();
		try {
			if(goods_message.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Goods_message resultSelect=iGoods_messageService.selectGoods_messageById(goods_message.getId()+"");
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
	@RequestMapping("/updateGoods_message")
	@ResponseBody
	public Map update(Goods_message goods_message){
		Map resultMap=new HashMap();
		try {
			if(goods_message.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iGoods_messageService.updateGoods_message(goods_message);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+goods_message.getId());
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
	@RequestMapping("/listGoods_message")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Goods_message goods_message)
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
				paramMap.put("id",goods_message.getId());
				paramMap.put("buyer_id",goods_message.getBuyer_id());
				paramMap.put("goods_id",goods_message.getGoods_id());
				paramMap.put("reply_goods_message_id",goods_message.getReply_goods_message_id());
				paramMap.put("message_type",goods_message.getMessage_type());
				paramMap.put("message_content",goods_message.getMessage_content());
				String message_dtFrom=request.getParameter("message_dtFrom");
				String message_dtTo=request.getParameter("message_dtTo");
				if(message_dtFrom!=null&&!message_dtFrom.equals(""))
				paramMap.put("message_dtFrom", sdf.parse(message_dtFrom));
				if(message_dtTo!=null&&!message_dtTo.equals(""))
				paramMap.put("message_dtTo", sdf.parse(message_dtTo));
				paramMap.put("status",goods_message.getStatus());
				String cd_dtFrom=request.getParameter("cd_dtFrom");
				String cd_dtTo=request.getParameter("cd_dtTo");
				if(cd_dtFrom!=null&&!cd_dtFrom.equals(""))
				paramMap.put("cd_dtFrom", sdf.parse(cd_dtFrom));
				if(cd_dtTo!=null&&!cd_dtTo.equals(""))
				paramMap.put("cd_dtTo", sdf.parse(cd_dtTo));
				String up_dtFrom=request.getParameter("up_dtFrom");
				String up_dtTo=request.getParameter("up_dtTo");
				if(up_dtFrom!=null&&!up_dtFrom.equals(""))
				paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
				if(up_dtTo!=null&&!up_dtTo.equals(""))
				paramMap.put("up_dtTo", sdf.parse(up_dtTo));
				List<Goods_message> list=iGoods_messageService.selectGoods_messageByParam(paramMap);
				int totalnumber=iGoods_messageService.selectCountGoods_messageByParam(paramMap);
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
