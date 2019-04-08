package com.deershop.controller.complain;
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
import com.deershop.service.complain.IComplainService;
import com.deershop.model.complain.Complain;
@Controller
public class ComplainController {
	@Autowired
	private IComplainService iComplainService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addComplain")
	@ResponseBody
	public Map add(Complain complain){
		Map resultMap=new HashMap();
		try {
			iComplainService.addComplain(complain);
			resultMap.put("status", "0");
			resultMap.put("msg", complain.getId());
			logger.info("新建成功，主键："+complain.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdComplain")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Complain complain){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Complain.class);
			List<Complain> list = (List<Complain>)objectMapper.readValue(data, javaType);
			iComplainService.muladdComplain(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+complain.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteComplain")
	@ResponseBody
	public Map delete(Complain complain){
		Map resultMap=new HashMap();
		try {
			if(complain.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iComplainService.deleteComplain(complain.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+complain.getId());
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
	@RequestMapping("/selectComplain")
	@ResponseBody
	public Map select(Complain complain){
		Map resultMap=new HashMap();
		try {
			if(complain.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Complain resultSelect=iComplainService.selectComplainById(complain.getId()+"");
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
	@RequestMapping("/updateComplain")
	@ResponseBody
	public Map update(Complain complain){
		Map resultMap=new HashMap();
		try {
			if(complain.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iComplainService.updateComplain(complain);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+complain.getId());
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
	@RequestMapping("/listComplain")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Complain complain)
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
				paramMap.put("id",complain.getId());
				paramMap.put("complainant_id",complain.getComplainant_id());
				paramMap.put("complainant_type",complain.getComplainant_type());
				paramMap.put("becomplained_id",complain.getBecomplained_id());
				paramMap.put("becomplained_type",complain.getBecomplained_type());
				paramMap.put("deal_people",complain.getDeal_people());
				paramMap.put("complain_type",complain.getComplain_type());
				paramMap.put("complain_content",complain.getComplain_content());
				paramMap.put("complain_img",complain.getComplain_img());
				paramMap.put("becomplained_desc",complain.getBecomplained_desc());
				paramMap.put("deal_result",complain.getDeal_result());
				paramMap.put("complainant",complain.getComplainant());
				paramMap.put("complainant_tel",complain.getComplainant_tel());
				String complain_dtFrom=request.getParameter("complain_dtFrom");
				String complain_dtTo=request.getParameter("complain_dtTo");
				if(complain_dtFrom!=null&&!complain_dtFrom.equals(""))
				paramMap.put("complain_dtFrom", sdf.parse(complain_dtFrom));
				if(complain_dtTo!=null&&!complain_dtTo.equals(""))
				paramMap.put("complain_dtTo", sdf.parse(complain_dtTo));
				String deal_dtFrom=request.getParameter("deal_dtFrom");
				String deal_dtTo=request.getParameter("deal_dtTo");
				if(deal_dtFrom!=null&&!deal_dtFrom.equals(""))
				paramMap.put("deal_dtFrom", sdf.parse(deal_dtFrom));
				if(deal_dtTo!=null&&!deal_dtTo.equals(""))
				paramMap.put("deal_dtTo", sdf.parse(deal_dtTo));
				String cancel_dtFrom=request.getParameter("cancel_dtFrom");
				String cancel_dtTo=request.getParameter("cancel_dtTo");
				if(cancel_dtFrom!=null&&!cancel_dtFrom.equals(""))
				paramMap.put("cancel_dtFrom", sdf.parse(cancel_dtFrom));
				if(cancel_dtTo!=null&&!cancel_dtTo.equals(""))
				paramMap.put("cancel_dtTo", sdf.parse(cancel_dtTo));
				paramMap.put("status",complain.getStatus());
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
				List<Complain> list=iComplainService.selectComplainByParam(paramMap);
				int totalnumber=iComplainService.selectCountComplainByParam(paramMap);
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
