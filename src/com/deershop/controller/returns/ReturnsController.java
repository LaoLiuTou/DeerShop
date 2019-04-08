package com.deershop.controller.returns;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deershop.model.returns.Returns;
import com.deershop.service.returns.IReturnsService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class ReturnsController {
	@Autowired
	private IReturnsService iReturnsService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addReturns")
	@ResponseBody
	public Map add(Returns returns){
		Map resultMap=new HashMap();
		try {
			iReturnsService.addReturns(returns);
			resultMap.put("status", "0");
			resultMap.put("msg", returns.getId());
			logger.info("新建成功，主键："+returns.getId());
			
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdReturns")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Returns returns){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Returns.class);
			List<Returns> list = (List<Returns>)objectMapper.readValue(data, javaType);
			iReturnsService.muladdReturns(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+returns.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteReturns")
	@ResponseBody
	public Map delete(Returns returns){
		Map resultMap=new HashMap();
		try {
			if(returns.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iReturnsService.deleteReturns(returns.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+returns.getId());
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
	@RequestMapping("/selectReturns")
	@ResponseBody
	public Map select(Returns returns){
		Map resultMap=new HashMap();
		try {
			if(returns.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Returns resultSelect=iReturnsService.selectReturnsById(returns.getId()+"");
				/////处理图片
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
				if(resultSelect.getImg()!=null){
					List<String> images=objectMapper.readValue(resultSelect.getImg(),javaType);
					if(images.size()>0){
						resultSelect.setFirstImg(images.get(0));
					}
				}
				/////
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
	@RequestMapping("/statusReturns")
	@ResponseBody
	public Map status(Returns returns){
		Map resultMap=new HashMap();
		try {
			if(returns.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Returns resultSelect=iReturnsService.selectReturnsById(returns.getId()+"");
				List<ReturnStatus> list = new ArrayList<ReturnStatus>();
				if(resultSelect.getReturn_ctg()!=null&&resultSelect.getReturn_ctg().equals("退款")){
					if(resultSelect.getSubmit_dt()!=null){
						ReturnStatus rs= new ReturnStatus();
						rs.setStatus("发起退货退款申请");
						rs.setDesc("退款原因："+resultSelect.getReturn_type()+"\n"+
								"退货金额："+resultSelect.getReturn_price());
						rs.setTime(resultSelect.getSubmit_dt());
						list.add(rs);
					}
					if(resultSelect.getIs_agree()!=null&&resultSelect.getIs_agree().equals("Y")){
						ReturnStatus rs= new ReturnStatus();
						rs.setStatus("卖家同意退款申请");
						rs.setDesc("同意退款");
						rs.setTime(resultSelect.getAgree_dt());
						list.add(rs);
					}
					if(resultSelect.getIs_agree()!=null&&resultSelect.getIs_agree().equals("N")){
						ReturnStatus rs= new ReturnStatus();
						rs.setStatus("卖家拒绝退款申请");
						rs.setDesc(resultSelect.getRefuse_reason());
						rs.setTime(resultSelect.getAgree_dt());
						list.add(rs);
					}
					if(resultSelect.getIs_agree()!=null&&resultSelect.getIs_agree().equals("Y")&&
							resultSelect.getIs_return()!=null&&resultSelect.getIs_return().equals("Y")){
						ReturnStatus rs= new ReturnStatus();
						rs.setStatus("退款成功");
						rs.setDesc("退款成功");
						rs.setTime(resultSelect.getReturn_dt());
						list.add(rs);
					}
				}
				else if(resultSelect.getReturn_ctg()!=null&&resultSelect.getReturn_ctg().equals("退货退款")){
					if(resultSelect.getSubmit_dt()!=null){
						ReturnStatus rs= new ReturnStatus();
						rs.setStatus("发起退货退款申请");
						rs.setDesc("退款原因："+resultSelect.getReturn_type()+"\n"+
								"退货金额："+resultSelect.getReturn_price());
						rs.setTime(resultSelect.getSubmit_dt());
						list.add(rs);
					}
					if(resultSelect.getIs_agree()!=null&&resultSelect.getIs_agree().equals("Y")){
						ReturnStatus rs= new ReturnStatus();
						rs.setStatus("卖家同意退款申请");
						rs.setDesc("同意退款");
						rs.setTime(resultSelect.getAgree_dt());
						list.add(rs);
					}
					if(resultSelect.getIs_agree()!=null&&resultSelect.getIs_agree().equals("Y")&&
							resultSelect.getIs_send()!=null&&resultSelect.getIs_send().equals("Y")){
						ReturnStatus rs= new ReturnStatus();
						rs.setStatus("退货信息");
						rs.setDesc("快递单号："+resultSelect.getExpress_code()+" "+resultSelect.getExpress());
						rs.setTime(resultSelect.getSend_dt());
						list.add(rs);
					}
					if(resultSelect.getIs_agree()!=null&&resultSelect.getIs_agree().equals("N")){
						ReturnStatus rs= new ReturnStatus();
						rs.setStatus("卖家拒绝退款申请");
						rs.setDesc(resultSelect.getRefuse_reason());
						rs.setTime(resultSelect.getAgree_dt());
						list.add(rs);
					}
					if(resultSelect.getIs_agree()!=null&&resultSelect.getIs_agree().equals("Y")&&
							resultSelect.getIs_send()!=null&&resultSelect.getIs_send().equals("Y")&&
							resultSelect.getIs_take()!=null&&resultSelect.getIs_take().equals("Y")&&
							resultSelect.getIs_return()!=null&&resultSelect.getIs_return().equals("Y")){
						ReturnStatus rs= new ReturnStatus();
						rs.setStatus("退款成功");
						rs.setDesc("退款成功");
						rs.setTime(resultSelect.getReturn_dt());
						list.add(rs);
					}
				}
				
				resultMap.put("status", "0");
				resultMap.put("msg", list);
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	class ReturnStatus{
		private String status;
		private String desc;
		private Date time;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public Date getTime() {
			return time;
		}
		public void setTime(Date time) {
			this.time = time;
		}
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/updateReturns")
	@ResponseBody
	public Map update(Returns returns){
		Map resultMap=new HashMap();
		try {
			if(returns.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iReturnsService.updateReturns(returns);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+returns.getId());
				
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
	@RequestMapping("/listReturns")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Returns returns)
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
				paramMap.put("id",returns.getId());
				paramMap.put("buyer_id",returns.getBuyer_id());
				paramMap.put("seller_id",returns.getSeller_id());
				paramMap.put("orders_id",returns.getOrders_id());
				paramMap.put("order_item_ids",returns.getOrder_item_ids());
				paramMap.put("initiator",returns.getInitiator());
				paramMap.put("return_type",returns.getReturn_type());
				paramMap.put("return_img",returns.getReturn_img());
				paramMap.put("problem",returns.getProblem());
				paramMap.put("send_type",returns.getSend_type());
				paramMap.put("return_price",returns.getReturn_price());
				paramMap.put("express",returns.getExpress());
				paramMap.put("express_code",returns.getExpress_code());
				paramMap.put("is_agree",returns.getIs_agree());
				paramMap.put("is_send",returns.getIs_send());
				paramMap.put("is_take",returns.getIs_take());
				paramMap.put("is_return",returns.getIs_return());
				paramMap.put("return_ctg",returns.getReturn_ctg());
				String submit_dtFrom=request.getParameter("submit_dtFrom");
				String submit_dtTo=request.getParameter("submit_dtTo");
				if(submit_dtFrom!=null&&!submit_dtFrom.equals(""))
				paramMap.put("submit_dtFrom", sdf.parse(submit_dtFrom));
				if(submit_dtTo!=null&&!submit_dtTo.equals(""))
				paramMap.put("submit_dtTo", sdf.parse(submit_dtTo));
				String agree_dtFrom=request.getParameter("agree_dtFrom");
				String agree_dtTo=request.getParameter("agree_dtTo");
				if(agree_dtFrom!=null&&!agree_dtFrom.equals(""))
				paramMap.put("agree_dtFrom", sdf.parse(agree_dtFrom));
				if(agree_dtTo!=null&&!agree_dtTo.equals(""))
				paramMap.put("agree_dtTo", sdf.parse(agree_dtTo));
				String send_dtFrom=request.getParameter("send_dtFrom");
				String send_dtTo=request.getParameter("send_dtTo");
				if(send_dtFrom!=null&&!send_dtFrom.equals(""))
				paramMap.put("send_dtFrom", sdf.parse(send_dtFrom));
				if(send_dtTo!=null&&!send_dtTo.equals(""))
				paramMap.put("send_dtTo", sdf.parse(send_dtTo));
				String take_dtFrom=request.getParameter("take_dtFrom");
				String take_dtTo=request.getParameter("take_dtTo");
				if(take_dtFrom!=null&&!take_dtFrom.equals(""))
				paramMap.put("take_dtFrom", sdf.parse(take_dtFrom));
				if(take_dtTo!=null&&!take_dtTo.equals(""))
				paramMap.put("take_dtTo", sdf.parse(take_dtTo));
				String return_dtFrom=request.getParameter("return_dtFrom");
				String return_dtTo=request.getParameter("return_dtTo");
				if(return_dtFrom!=null&&!return_dtFrom.equals(""))
				paramMap.put("return_dtFrom", sdf.parse(return_dtFrom));
				if(return_dtTo!=null&&!return_dtTo.equals(""))
				paramMap.put("return_dtTo", sdf.parse(return_dtTo));
				paramMap.put("status",returns.getStatus());
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
				paramMap.put("return_code", returns.getReturn_code());
				paramMap.put("order_num", returns.getOrder_num());
				List<Returns> list=iReturnsService.selectReturnsByParam(paramMap);
				int totalnumber=iReturnsService.selectCountReturnsByParam(paramMap);
				/////处理图片
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
				for(int index=0;index<list.size();index++){
					if(list.get(index).getImg()!=null){
						List<String> images=objectMapper.readValue(list.get(index).getImg(),javaType);
						if(images.size()>0){
							list.get(index).setFirstImg(images.get(0));
						}
					}
				}
				/////
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
