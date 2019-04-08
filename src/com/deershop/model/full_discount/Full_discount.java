package com.deershop.model.full_discount;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Full_discount {

	/** 默认主键 */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** 商家ID */
	private  Long seller_id;
	public Long getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(Long seller_id) {
		this.seller_id = seller_id;
	}
	/** 满减类型 */
	private  String full_type;
	public String getFull_type() {
		return full_type;
	}
	public void setFull_type(String full_type) {
		this.full_type = full_type;
	}
	/** 满减名称 */
	private  String full_name;
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	/** 满减规则 */
	private  String rule;
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	/** 满减说明 */
	private  String full_desc;
	public String getFull_desc() {
		return full_desc;
	}
	public void setFull_desc(String full_desc) {
		this.full_desc = full_desc;
	}
	/** 是否全部商品 */
	private  String is_all;
	public String getIs_all() {
		return is_all;
	}
	public void setIs_all(String is_all) {
		this.is_all = is_all;
	}
	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date create_dt;
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	/** 使用开始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date st_dt;
	public Date getSt_dt() {
		return st_dt;
	}
	public void setSt_dt(Date st_dt) {
		this.st_dt = st_dt;
	}
	/** 使用结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date ed_dt;
	public Date getEd_dt() {
		return ed_dt;
	}
	public void setEd_dt(Date ed_dt) {
		this.ed_dt = ed_dt;
	}
	/** 停止时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date stop_dt;
	public Date getStop_dt() {
		return stop_dt;
	}
	public void setStop_dt(Date stop_dt) {
		this.stop_dt = stop_dt;
	}
	/** 发行状态 */
	private  String publish_status;
	public String getPublish_status() {
		return publish_status;
	}
	public void setPublish_status(String publish_status) {
		this.publish_status = publish_status;
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



}
