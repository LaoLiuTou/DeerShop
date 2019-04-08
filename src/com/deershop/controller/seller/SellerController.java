package com.deershop.controller.seller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
import com.deershop.service.seller.ISellerService;
import com.deershop.utils.MD5Encryption;
import com.deershop.utils.TokenUtils;
import com.deershop.utils.TokenUtils.TokenBean;
import com.deershop.model.seller.Seller;
import com.deershop.model.user.User;
@Controller
public class SellerController {
	@Autowired
	private ISellerService iSellerService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addSeller")
	@ResponseBody
	public Map add(Seller seller){
		Map resultMap=new HashMap();
		try {
			if(seller.getShop_tel()!=null&&seller.getPwd()!=null){
				
				Map paramMap=new HashMap();
				paramMap.put("fromPage",0);
				paramMap.put("toPage",1); 
				paramMap.put("shop_tel",seller.getShop_tel());
				List<Seller> list=iSellerService.selectSellerByParam(paramMap);
				if(list.size()==0){
					String password=MD5Encryption.getEncryption(seller.getPwd()).toLowerCase();
					seller.setPwd(password);
					iSellerService.addSeller(seller);
					resultMap.put("status", "0");
					resultMap.put("msg", seller.getId());
					logger.info("新建成功，主键："+seller.getId());
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "用户名已存在！");
				}
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "用户名或密码不能为空！");
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
	@RequestMapping("/muladdSeller")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Seller seller){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Seller.class);
			List<Seller> list = (List<Seller>)objectMapper.readValue(data, javaType);
			iSellerService.muladdSeller(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+seller.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteSeller")
	@ResponseBody
	public Map delete(Seller seller){
		Map resultMap=new HashMap();
		try {
			if(seller.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iSellerService.deleteSeller(seller.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+seller.getId());
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
	@RequestMapping("/selectSeller")
	@ResponseBody
	public Map select(Seller seller){
		Map resultMap=new HashMap();
		try {
			if(seller.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Seller resultSelect=iSellerService.selectSellerById(seller.getId()+"");
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
	@RequestMapping("/updateSeller")
	@ResponseBody
	public Map update(Seller seller){
		Map resultMap=new HashMap();
		try {
			if(seller.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				if(seller.getPwd()!=null){
					String password=MD5Encryption.getEncryption(seller.getPwd()).toLowerCase();
					seller.setPwd(password);
				}
				int resultUpdate=iSellerService.updateSeller(seller);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+seller.getId());
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
	@RequestMapping("/listSeller")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Seller seller)
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
				paramMap.put("id",seller.getId());
				paramMap.put("c_id",seller.getC_id());
				paramMap.put("old_seller_num",seller.getOld_seller_num());
				paramMap.put("shop_name",seller.getShop_name());
				paramMap.put("shop_desc",seller.getShop_desc());
				paramMap.put("shop_tel",seller.getShop_tel());
				paramMap.put("pwd",seller.getPwd());
				paramMap.put("shop_addr",seller.getShop_addr());
				paramMap.put("shop_img",seller.getShop_img());
				paramMap.put("wechat",seller.getWechat());
				paramMap.put("openid",seller.getOpenid());
				paramMap.put("return_info",seller.getReturn_info());
				paramMap.put("all_income",seller.getAll_income());
				paramMap.put("no_clear_income",seller.getNo_clear_income());
				paramMap.put("cash",seller.getCash());
				paramMap.put("caution_money",seller.getCaution_money());
				paramMap.put("status",seller.getStatus());
				paramMap.put("audit_mem_id",seller.getAudit_mem_id());
				paramMap.put("audit_status",seller.getAudit_status());
				paramMap.put("is_order_notice",seller.getIs_order_notice());
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
				
				//search
				String search=request.getParameter("search");
				paramMap.put("search", search);


				List<Seller> list=iSellerService.selectSellerByParam(paramMap);
				int totalnumber=iSellerService.selectCountSellerByParam(paramMap);
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/allSeller")
	@ResponseBody
	public Map all(HttpServletRequest request, HttpServletResponse response,Seller seller)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			Map paramMap=new HashMap();
			
			paramMap.put("orderBy","ID DESC"); 
			paramMap.put("id",seller.getId());
			paramMap.put("c_id",seller.getC_id());
			paramMap.put("old_seller_num",seller.getOld_seller_num());
			paramMap.put("shop_name",seller.getShop_name());
			paramMap.put("shop_desc",seller.getShop_desc());
			paramMap.put("shop_tel",seller.getShop_tel());
			paramMap.put("pwd",seller.getPwd());
			paramMap.put("shop_addr",seller.getShop_addr());
			paramMap.put("shop_img",seller.getShop_img());
			paramMap.put("wechat",seller.getWechat());
			paramMap.put("openid",seller.getOpenid());
			paramMap.put("return_info",seller.getReturn_info());
			paramMap.put("all_income",seller.getAll_income());
			paramMap.put("no_clear_income",seller.getNo_clear_income());
			paramMap.put("cash",seller.getCash());
			paramMap.put("caution_money",seller.getCaution_money());
			paramMap.put("status",seller.getStatus());
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
			paramMap.put("audit_mem_id", seller.getAudit_mem_id());
			paramMap.put("audit_status", seller.getAudit_status());
			paramMap.put("is_order_notice", seller.getIs_order_notice());
			int totalnumber=iSellerService.selectCountSellerByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			List<Seller> list=iSellerService.selectSellerByParam(paramMap);
			Map tempMap=new HashMap();
			resultMap.put("status", "0");
			tempMap.put("num", totalnumber);
			tempMap.put("data", list);
			resultMap.put("msg", tempMap);
			 
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/loginSeller")
	@ResponseBody
	public Map login(HttpServletRequest request,Seller seller){
		Map resultMap=new HashMap();
		try {
			if(seller.getShop_tel()!=null&&seller.getPwd()!=null){
				
				Map paramMap=new HashMap();
				paramMap.put("fromPage",0);
				paramMap.put("toPage",1); 
				paramMap.put("shop_tel",seller.getShop_tel());
				//paramMap.put("audit_status","已审核");
				List<Seller> list=iSellerService.selectSellerByParam(paramMap);
				if(list.size()>0){
					
					String localpwdStr=(MD5Encryption.getEncryption(list.get(0).getShop_tel()).substring(0, 16)+
							list.get(0).getPwd().substring(0,16)).toLowerCase();
					if(localpwdStr.equals(seller.getPwd().toLowerCase())){
						
						if(list.get(0).getAudit_status()!=null&&list.get(0).getAudit_status().equals("已审核")){
							resultMap.put("status", "0");
							resultMap.put("msg", list.get(0));
							logger.info("用户登录："+list.get(0).getShop_tel());
						}
						else if(list.get(0).getAudit_status()!=null&&list.get(0).getAudit_status().equals("未审核")){
							resultMap.put("status", "-1");
							resultMap.put("msg", "该账号正在审核！");
							
						}
						else if(list.get(0).getAudit_status()!=null&&list.get(0).getAudit_status().equals("已驳回")){
							resultMap.put("status", "-2");
							resultMap.put("msg", list.get(0));
							
						}
						
					}
					else{
						resultMap.put("status", "-1");
						resultMap.put("msg", "密码错误！");
					}
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "用户名不存在！");
				}
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "用户名或密码不能为空！");
			}
			 
			 
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "登录失败！");
			logger.info("登录失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/loginbywechat")
	@ResponseBody
	public Map loginbywechat(HttpServletRequest request,Seller seller){
		Map resultMap=new HashMap();
		try {
			if(seller.getOpenid()!=null){
				
				Map paramMap=new HashMap();
				paramMap.put("fromPage",0);
				paramMap.put("toPage",1); 
				paramMap.put("openid",seller.getOpenid());
				//paramMap.put("audit_status","已审核");
				List<Seller> list=iSellerService.selectSellerByParam(paramMap);
				if(list.size()>0){
					
						
					if(list.get(0).getAudit_status()!=null&&list.get(0).getAudit_status().equals("已审核")){
						resultMap.put("status", "0");
						resultMap.put("msg", list.get(0));
						logger.info("用户登录："+list.get(0).getShop_tel());
					}
					else if(list.get(0).getAudit_status()!=null&&list.get(0).getAudit_status().equals("未审核")){
						resultMap.put("status", "-1");
						resultMap.put("msg", "该账号正在审核！");
						
					}
					else if(list.get(0).getAudit_status()!=null&&list.get(0).getAudit_status().equals("已驳回")){
						resultMap.put("status", "-2");
						resultMap.put("msg", list.get(0));
						
					}
					
				}
				else{
					resultMap.put("status", "-3");
					resultMap.put("msg", "用户不存在！");
				}
			}
			else{
				resultMap.put("status", "-4");
				resultMap.put("msg", "openid不能为空！");
			}
			 
			 
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "登录失败！");
			logger.info("登录失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/updateSellerPwd")
	@ResponseBody
	public Map updateSellerPwd(Seller seller){
		Map resultMap=new HashMap();
		try {
			Map paramMap=new HashMap();
			paramMap.put("fromPage",0);
			paramMap.put("toPage",1); 
			paramMap.put("shop_tel",seller.getShop_tel());
			//paramMap.put("audit_status","已审核");
			List<Seller> list=iSellerService.selectSellerByParam(paramMap);
			if(list.size()>0){
				Seller temp = new Seller();
				if(list.get(0).getAudit_status()!=null&&list.get(0).getAudit_status().equals("已审核")){
					temp.setId(list.get(0).getId());
					if(seller.getPwd()!=null){
						String password=MD5Encryption.getEncryption(seller.getPwd()).toLowerCase();
						temp.setPwd(password);
					}
					
					iSellerService.updateSeller(temp);
					resultMap.put("status", "0");
					resultMap.put("msg", "修改成功！");
					logger.info("更新成功，主键："+seller.getId());
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "该账号未通过审核！");
				}
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "更新失败！");
			logger.info("更新失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
}
