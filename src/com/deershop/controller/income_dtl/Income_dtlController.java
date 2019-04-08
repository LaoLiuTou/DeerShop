package com.deershop.controller.income_dtl;
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
import com.deershop.service.income_dtl.IIncome_dtlService;
import com.deershop.model.income_dtl.Income_dtl;
@Controller
public class Income_dtlController {
	@Autowired
	private IIncome_dtlService iIncome_dtlService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addIncome_dtl")
	@ResponseBody
	public Map add(Income_dtl income_dtl){
		Map resultMap=new HashMap();
		try {
			iIncome_dtlService.addIncome_dtl(income_dtl);
			resultMap.put("status", "0");
			resultMap.put("msg", income_dtl.getId());
			logger.info("新建成功，主键："+income_dtl.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdIncome_dtl")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Income_dtl income_dtl){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Income_dtl.class);
			List<Income_dtl> list = (List<Income_dtl>)objectMapper.readValue(data, javaType);
			iIncome_dtlService.muladdIncome_dtl(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+income_dtl.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteIncome_dtl")
	@ResponseBody
	public Map delete(Income_dtl income_dtl){
		Map resultMap=new HashMap();
		try {
			if(income_dtl.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iIncome_dtlService.deleteIncome_dtl(income_dtl.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+income_dtl.getId());
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
	@RequestMapping("/selectIncome_dtl")
	@ResponseBody
	public Map select(Income_dtl income_dtl){
		Map resultMap=new HashMap();
		try {
			if(income_dtl.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Income_dtl resultSelect=iIncome_dtlService.selectIncome_dtlById(income_dtl.getId()+"");
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
	@RequestMapping("/updateIncome_dtl")
	@ResponseBody
	public Map update(Income_dtl income_dtl){
		Map resultMap=new HashMap();
		try {
			if(income_dtl.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iIncome_dtlService.updateIncome_dtl(income_dtl);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+income_dtl.getId());
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
	@RequestMapping("/listIncome_dtl")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Income_dtl income_dtl)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String page=request.getParameter("page");
			String size=request.getParameter("size");
			if(page!=null&&size!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",(Integer.parseInt(page)-1)*Integer.parseInt(size));
				paramMap.put("toPage",Integer.parseInt(size)); 
				paramMap.put("orderBy","OPERATE_DT DESC"); 
				paramMap.put("id",income_dtl.getId());
				paramMap.put("c_id",income_dtl.getC_id());
				paramMap.put("seller_id",income_dtl.getSeller_id());
				paramMap.put("orders_id",income_dtl.getOrders_id());
				paramMap.put("income_type",income_dtl.getIncome_type());
				paramMap.put("income_item",income_dtl.getIncome_item());
				paramMap.put("price",income_dtl.getPrice());
				String operate_dtFrom=request.getParameter("operate_dtFrom");
				String operate_dtTo=request.getParameter("operate_dtTo");
				if(operate_dtFrom!=null&&!operate_dtFrom.equals(""))
				paramMap.put("operate_dtFrom", sdf.parse((operate_dtFrom+" 00:00:00")));
				if(operate_dtTo!=null&&!operate_dtTo.equals(""))
				paramMap.put("operate_dtTo", sdf.parse((operate_dtTo+" 23:59:59")));
				List<Income_dtl> list=iIncome_dtlService.selectIncome_dtlByParam(paramMap);
				int totalnumber=iIncome_dtlService.selectCountIncome_dtlByParam(paramMap);
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
