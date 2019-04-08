package com.deershop.controller.user;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deershop.model.seller.Seller;
import com.deershop.model.user.User;
import com.deershop.service.user.IUserService;
import com.deershop.utils.MD5Encryption;
import com.deershop.utils.TokenUtils;
import com.deershop.utils.TokenUtils.TokenBean;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class UserController {
	@Autowired
	private IUserService iUserService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addUser")
	@ResponseBody
	public Map add(User user){
		Map resultMap=new HashMap();
		try {
			if(user.getUsername()!=null&&user.getPassword()!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",0);
				paramMap.put("toPage",1); 
				paramMap.put("username",user.getUsername());
				List<User> list=iUserService.selectUserByParam(paramMap);
				if(list.size()==0){
					String password=MD5Encryption.getEncryption(user.getPassword()).toLowerCase();
					user.setPassword(password);
					iUserService.addUser(user);
					resultMap.put("status", "0");
					resultMap.put("msg", user.getId());
					logger.info("新建成功，主键："+user.getId());
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
	@RequestMapping("/muladdUser")
	@ResponseBody
	public Map muladd(HttpServletRequest request,User user){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, User.class);
			List<User> list = (List<User>)objectMapper.readValue(data, javaType);
			iUserService.muladdUser(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+user.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Map delete(User user){
		Map resultMap=new HashMap();
		try {
			if(user.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iUserService.deleteUser(user.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+user.getId());
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
	@RequestMapping("/selectUser")
	@ResponseBody
	public Map select(User user){
		Map resultMap=new HashMap();
		try {
			if(user.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				User resultSelect=iUserService.selectUserById(user.getId()+"");
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
	@RequestMapping("/updateUser")
	@ResponseBody
	public Map update(User user){
		Map resultMap=new HashMap();
		try {
			if(user.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				if(user.getPassword()!=null){
					String password=MD5Encryption.getEncryption(user.getPassword()).toLowerCase();
					user.setPassword(password);
				}
				int resultUpdate=iUserService.updateUser(user);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+user.getId());
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
	@RequestMapping("/changePasswordUser")
	@ResponseBody
	public Map changePasswordUser(HttpServletRequest request,User user){
		Map resultMap=new HashMap();
		try {
			if(user.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				String old_password=request.getParameter("old_password");
				String new_password=request.getParameter("new_password");
				User temp=iUserService.selectUserById(user.getId()+"");
				if(temp!=null){
					if(temp.getPassword().equals(MD5Encryption.getEncryption(old_password).toLowerCase())){
						user.setPassword(MD5Encryption.getEncryption(new_password).toLowerCase());
						int resultUpdate=iUserService.updateUser(user);
						resultMap.put("status", "0");
						resultMap.put("msg", "更新成功！");
						logger.info("更新成功，主键："+user.getId());
					}
					else{
						resultMap.put("status", "-1");
						resultMap.put("msg", "原密码错误！");	
					}
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listUser")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,User user)
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
				paramMap.put("id",user.getId());
				paramMap.put("username",user.getUsername());
				paramMap.put("password",user.getPassword());
				paramMap.put("nickname",user.getNickname());
				paramMap.put("comment",user.getComment());
				paramMap.put("c_id",user.getC_id());
				paramMap.put("state",user.getState());
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
				
				
				List<User> list=iUserService.selectUserByParam(paramMap);
				int totalnumber=iUserService.selectCountUserByParam(paramMap);
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
	@RequestMapping("/login")
	@ResponseBody
	public Map login(HttpServletRequest request,User user){
		Map resultMap=new HashMap();
		try {
			if(user.getUsername()!=null&&user.getPassword()!=null){
				
				Map paramMap=new HashMap();
				paramMap.put("fromPage",0);
				paramMap.put("toPage",1); 
				paramMap.put("username",user.getUsername());
				paramMap.put("state",0);
				List<User> list=iUserService.selectUserByParam(paramMap);
				if(list.size()>0){
					if(list.get(0).getPassword().equals(MD5Encryption.getEncryption(user.getPassword()).toLowerCase())){
						String userToken= UUID.randomUUID().toString();
						 
						resultMap.put("status", "0");
						resultMap.put("token", userToken);
						resultMap.put("msg", list.get(0));
						logger.info("用户登录："+list.get(0).getUsername());
						TokenUtils.TokenBean tokenBean =new TokenUtils().new TokenBean();
						tokenBean.setTimesamp(System.currentTimeMillis()+"");
						tokenBean.setUsername(list.get(0).getUsername());
						tokenBean.setUserid(list.get(0).getId()+"");
	  					TokenUtils.add(userToken, tokenBean);
					}
					else{
						resultMap.put("status", "-1");
						resultMap.put("msg", "密码错误！");
					}
					
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("msg", "用户不存在，或已禁用！");
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
	@RequestMapping("/passwordUser")
	@ResponseBody
	public Map password(User user){
		Map resultMap=new HashMap();
		try {
			if(user.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				User temp=iUserService.selectUserById(user.getId()+"");
				String pass=MD5Encryption.getEncryption(user.getPassword()).toLowerCase();
				if(!pass.equals(temp.getPassword().toLowerCase())){
					resultMap.put("status", "-1");
					resultMap.put("msg", "密码不一致！");
				}
				else{
					int resultUpdate=iUserService.updateUser(user);
					resultMap.put("status", "0");
					resultMap.put("msg", "更新成功！");
					logger.info("更新成功，主键："+user.getId());
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
