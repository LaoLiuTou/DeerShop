package com.deershop.model.goods_category;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Goods_category {

	/** 默认主键 */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** 上级id */
	private  Long pid;
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/** 分类名称 */
	private  String ctg_name;
	public String getCtg_name() {
		return ctg_name;
	}
	public void setCtg_name(String ctg_name) {
		this.ctg_name = ctg_name;
	}
	/** 功效 */
	private String effect;
	
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	/** 状态 */
	private  String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date cd_dt;
	public Date getCd_dt() {
		return cd_dt;
	}
	public void setCd_dt(Date cd_dt) {
		this.cd_dt = cd_dt;
	}
	/** 修改时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date up_dt;
	public Date getUp_dt() {
		return up_dt;
	}
	public void setUp_dt(Date up_dt) {
		this.up_dt = up_dt;
	}

	private String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	private Long seq;
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	 
	private String deer_flag;
	
	public String getDeer_flag() {
		return deer_flag;
	}
	public void setDeer_flag(String deer_flag) {
		this.deer_flag = deer_flag;
	}
	private List<Goods_category> subcategory;
	public List<Goods_category> getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(List<Goods_category> subcategory) {
		this.subcategory = subcategory;
	}
	

}
