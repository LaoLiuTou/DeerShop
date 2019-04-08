package com.deershop.model.complain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Complain {

	/** 默认主键 */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** 投诉人ID(商家ID/消费者ID) */
	private  Long complainant_id;
	public Long getComplainant_id() {
		return complainant_id;
	}
	public void setComplainant_id(Long complainant_id) {
		this.complainant_id = complainant_id;
	}
	/** 投诉人分类 */
	private  String complainant_type;
	public String getComplainant_type() {
		return complainant_type;
	}
	public void setComplainant_type(String complainant_type) {
		this.complainant_type = complainant_type;
	}
	/** 被投诉人ID(商家ID/消费者ID) */
	private  Long becomplained_id;
	public Long getBecomplained_id() {
		return becomplained_id;
	}
	public void setBecomplained_id(Long becomplained_id) {
		this.becomplained_id = becomplained_id;
	}
	/** 被投诉人分类 */
	private  String becomplained_type;
	public String getBecomplained_type() {
		return becomplained_type;
	}
	public void setBecomplained_type(String becomplained_type) {
		this.becomplained_type = becomplained_type;
	}
	/** 处理人 */
	private  String deal_people;
	public String getDeal_people() {
		return deal_people;
	}
	public void setDeal_people(String deal_people) {
		this.deal_people = deal_people;
	}
	/** 投诉类型 */
	private  String complain_type;
	public String getComplain_type() {
		return complain_type;
	}
	public void setComplain_type(String complain_type) {
		this.complain_type = complain_type;
	}
	/** 投诉内容 */
	private  String complain_content;
	public String getComplain_content() {
		return complain_content;
	}
	public void setComplain_content(String complain_content) {
		this.complain_content = complain_content;
	}
	/** 投诉照片 */
	private  String complain_img;
	public String getComplain_img() {
		return complain_img;
	}
	public void setComplain_img(String complain_img) {
		this.complain_img = complain_img;
	}
	/** 被投诉人陈述 */
	private  String becomplained_desc;
	public String getBecomplained_desc() {
		return becomplained_desc;
	}
	public void setBecomplained_desc(String becomplained_desc) {
		this.becomplained_desc = becomplained_desc;
	}
	/** 处理结果 */
	private  String deal_result;
	public String getDeal_result() {
		return deal_result;
	}
	public void setDeal_result(String deal_result) {
		this.deal_result = deal_result;
	}
	/** 投诉联系人 */
	private  String complainant;
	public String getComplainant() {
		return complainant;
	}
	public void setComplainant(String complainant) {
		this.complainant = complainant;
	}
	/** 投诉联系电话 */
	private  String complainant_tel;
	public String getComplainant_tel() {
		return complainant_tel;
	}
	public void setComplainant_tel(String complainant_tel) {
		this.complainant_tel = complainant_tel;
	}
	/** 投诉时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date complain_dt;
	public Date getComplain_dt() {
		return complain_dt;
	}
	public void setComplain_dt(Date complain_dt) {
		this.complain_dt = complain_dt;
	}
	/** 处理时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date deal_dt;
	public Date getDeal_dt() {
		return deal_dt;
	}
	public void setDeal_dt(Date deal_dt) {
		this.deal_dt = deal_dt;
	}
	/** 撤销时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date cancel_dt;
	public Date getCancel_dt() {
		return cancel_dt;
	}
	public void setCancel_dt(Date cancel_dt) {
		this.cancel_dt = cancel_dt;
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



}
