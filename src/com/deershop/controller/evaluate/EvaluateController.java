package com.deershop.controller.evaluate;
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
import com.deershop.service.evaluate.IEvaluateService;
import com.deershop.model.evaluate.Evaluate;
@Controller
public class EvaluateController {
	@Autowired
	private IEvaluateService iEvaluateService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("DeerShopLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addEvaluate")
	@ResponseBody
	public Map add(Evaluate evaluate){
		Map resultMap=new HashMap();
		try {
			iEvaluateService.addEvaluate(evaluate);
			resultMap.put("status", "0");
			resultMap.put("msg", evaluate.getId());
			logger.info("新建成功，主键："+evaluate.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdEvaluate")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Evaluate evaluate){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Evaluate.class);
			List<Evaluate> list = (List<Evaluate>)objectMapper.readValue(data, javaType);
			iEvaluateService.muladdEvaluate(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+evaluate.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteEvaluate")
	@ResponseBody
	public Map delete(Evaluate evaluate){
		Map resultMap=new HashMap();
		try {
			if(evaluate.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iEvaluateService.deleteEvaluate(evaluate.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+evaluate.getId());
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
	@RequestMapping("/selectEvaluate")
	@ResponseBody
	public Map select(Evaluate evaluate){
		Map resultMap=new HashMap();
		try {
			if(evaluate.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Evaluate resultSelect=iEvaluateService.selectEvaluateById(evaluate.getId()+"");
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
	@RequestMapping("/updateEvaluate")
	@ResponseBody
	public Map update(Evaluate evaluate){
		Map resultMap=new HashMap();
		try {
			if(evaluate.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iEvaluateService.updateEvaluate(evaluate);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+evaluate.getId());
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
	@RequestMapping("/listEvaluate")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Evaluate evaluate)
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
				paramMap.put("id",evaluate.getId());
				paramMap.put("pid",evaluate.getPid());
				paramMap.put("buyer_id",evaluate.getBuyer_id());
				paramMap.put("goods_id",evaluate.getGoods_id());
				paramMap.put("evaluate_comment",evaluate.getEvaluate_comment());
				paramMap.put("img",evaluate.getImg());
				paramMap.put("all_score",evaluate.getAll_score());
				paramMap.put("express_score",evaluate.getExpress_score());
				paramMap.put("goods_score",evaluate.getGoods_score());
				String evaluate_dtFrom=request.getParameter("evaluate_dtFrom");
				String evaluate_dtTo=request.getParameter("evaluate_dtTo");
				if(evaluate_dtFrom!=null&&!evaluate_dtFrom.equals(""))
				paramMap.put("evaluate_dtFrom", sdf.parse(evaluate_dtFrom));
				if(evaluate_dtTo!=null&&!evaluate_dtTo.equals(""))
				paramMap.put("evaluate_dtTo", sdf.parse(evaluate_dtTo));
				paramMap.put("status",evaluate.getStatus());
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
				List<Evaluate> list=iEvaluateService.selectEvaluateByParam(paramMap);
				int totalnumber=iEvaluateService.selectCountEvaluateByParam(paramMap);
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
