package com.deershop.controller.cart;
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
import com.deershop.service.cart.ICartService;
import com.deershop.model.cart.Cart;
@Controller
public class CartController {
	@Autowired
	private ICartService iCartService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addCart")
	@ResponseBody
	public Map add(Cart cart){
		Map resultMap=new HashMap();
		try {
			iCartService.addCart(cart);
			resultMap.put("status", "0");
			resultMap.put("msg", cart.getId());
			logger.info("新建成功，主键："+cart.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdCart")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Cart cart){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Cart.class);
			List<Cart> list = (List<Cart>)objectMapper.readValue(data, javaType);
			iCartService.muladdCart(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+cart.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteCart")
	@ResponseBody
	public Map delete(Cart cart,HttpServletRequest request){
		Map resultMap=new HashMap();
		String ids=request.getParameter("ids");
		try {
			if(ids==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iCartService.deleteCart(ids);
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+ids);
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
	@RequestMapping("/selectCart")
	@ResponseBody
	public Map select(Cart cart){
		Map resultMap=new HashMap();
		try {
			if(cart.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Cart resultSelect=iCartService.selectCartById(cart.getId()+"");
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
	@RequestMapping("/updateCart")
	@ResponseBody
	public Map update(Cart cart){
		Map resultMap=new HashMap();
		try {
			if(cart.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iCartService.updateCart(cart);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+cart.getId());
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
	@RequestMapping("/listCart")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Cart cart)
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
				paramMap.put("id",cart.getId());
				paramMap.put("c_id",cart.getC_id());
				paramMap.put("buyer_id",cart.getBuyer_id());
				paramMap.put("goods_id",cart.getGoods_id());
				paramMap.put("amount",cart.getAmount());
				String cart_dtFrom=request.getParameter("cart_dtFrom");
				String cart_dtTo=request.getParameter("cart_dtTo");
				if(cart_dtFrom!=null&&!cart_dtFrom.equals(""))
				paramMap.put("cart_dtFrom", sdf.parse(cart_dtFrom));
				if(cart_dtTo!=null&&!cart_dtTo.equals(""))
				paramMap.put("cart_dtTo", sdf.parse(cart_dtTo));
				paramMap.put("check", cart.getCheck());
				List<Cart> list=iCartService.selectCartByParam(paramMap);
				int totalnumber=iCartService.selectCountCartByParam(paramMap);
				/////处理图片
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
				for(int index=0;index<list.size();index++){
					List<String> images=objectMapper.readValue(list.get(index).getImg(),javaType);
					if(images.size()>0){
						list.get(index).setFirstImg(images.get(0));
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
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/groupCart")
	@ResponseBody
	public Map group(HttpServletRequest request, HttpServletResponse response,Cart cart)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			
				Map paramMap=new HashMap();
				paramMap.put("orderBy","ID DESC"); 
				paramMap.put("id",cart.getId());
				paramMap.put("c_id",cart.getC_id());
				paramMap.put("buyer_id",cart.getBuyer_id());
				paramMap.put("goods_id",cart.getGoods_id());
				paramMap.put("amount",cart.getAmount());
				String cart_dtFrom=request.getParameter("cart_dtFrom");
				String cart_dtTo=request.getParameter("cart_dtTo");
				if(cart_dtFrom!=null&&!cart_dtFrom.equals(""))
				paramMap.put("cart_dtFrom", sdf.parse(cart_dtFrom));
				if(cart_dtTo!=null&&!cart_dtTo.equals(""))
				paramMap.put("cart_dtTo", sdf.parse(cart_dtTo));
				paramMap.put("check", cart.getCheck());
				int totalnumber=iCartService.selectCountCartByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",totalnumber); 
				List<Cart> list=iCartService.selectCartByParam(paramMap);
				/////处理图片
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
				for(int index=0;index<list.size();index++){
					List<String> images=objectMapper.readValue(list.get(index).getImg(),javaType);
					if(images.size()>0){
						list.get(index).setFirstImg(images.get(0));
					}
				}
				
				//分组
				List<GroupCarts> groupCartsList = new ArrayList<GroupCarts>();
		        for (Cart c : list) {
		        	boolean flag=true;
		        	for(GroupCarts gc:groupCartsList){
		        		if(c.getSeller_id()!=null&c.getSeller_id()==gc.getSeller_id()){
		        			gc.getList().add(c);
		        			flag=false;
		        		}
		        	}
		        	if(flag){
		        		GroupCarts temp= new GroupCarts();
		        		temp.setSeller_id(c.getSeller_id());
		        		temp.setShop_name(c.getShop_name());
		        		temp.setCheck("false");
		        		temp.setIs_limit_amout(c.getIs_limit_amount());
		        		List<Cart> tempList= new ArrayList<Cart>();
		        		tempList.add(c);
		        		temp.setList(tempList);
		        		groupCartsList.add(temp);
		        	}
		            
		        }
		        	  
				/////
				Map tempMap=new HashMap();
				resultMap.put("status", "0");
				tempMap.put("num", totalnumber);
				tempMap.put("data", groupCartsList);
				resultMap.put("msg", tempMap);
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	
	class GroupCarts {
		private Long seller_id;
		private String shop_name;
		private String check;
		private String is_limit_amout;
		private List<Cart> list;
		public Long getSeller_id() {
			return seller_id;
		}
		public void setSeller_id(Long seller_id) {
			this.seller_id = seller_id;
		}
		public String getShop_name() {
			return shop_name;
		}
		public void setShop_name(String shop_name) {
			this.shop_name = shop_name;
		}
		public List<Cart> getList() {
			return list;
		}
		public void setList(List<Cart> list) {
			this.list = list;
		}
		public String getCheck() {
			return check;
		}
		public void setCheck(String check) {
			this.check = check;
		}
		public String getIs_limit_amout() {
			return is_limit_amout;
		}
		public void setIs_limit_amout(String is_limit_amout) {
			this.is_limit_amout = is_limit_amout;
		}
		
	}
}
