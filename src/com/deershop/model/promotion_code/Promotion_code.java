package com.deershop.model.promotion_code;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Promotion_code {

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
	/** 优惠券类型 */
	private  String promotion_type;
	public String getPromotion_type() {
		return promotion_type;
	}
	public void setPromotion_type(String promotion_type) {
		this.promotion_type = promotion_type;
	}
	/** 优惠券名称 */
	private  String promotion_name;
	public String getPromotion_name() {
		return promotion_name;
	}
	public void setPromotion_name(String promotion_name) {
		this.promotion_name = promotion_name;
	}
	/** 优惠规则 */
	private  String rule;
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	/** 优惠券说明 */
	private  String promotion_desc;
	public String getPromotion_desc() {
		return promotion_desc;
	}
	public void setPromotion_desc(String promotion_desc) {
		this.promotion_desc = promotion_desc;
	}
	/** 发行数量 */
	private  Long publish_amount;
	public Long getPublish_amount() {
		return publish_amount;
	}
	public void setPublish_amount(Long publish_amount) {
		this.publish_amount = publish_amount;
	}
	/** 现有数量 */
	private  Long now_amount;
	public Long getNow_amount() {
		return now_amount;
	}
	public void setNow_amount(Long now_amount) {
		this.now_amount = now_amount;
	}
	/** 每用户可领用数量 */
	private  Long limit_amount;
	public Long getLimit_amount() {
		return limit_amount;
	}
	public void setLimit_amount(Long limit_amount) {
		this.limit_amount = limit_amount;
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
	/** 发行时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date publish_dt;
	public Date getPublish_dt() {
		return publish_dt;
	}
	public void setPublish_dt(Date publish_dt) {
		this.publish_dt = publish_dt;
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
	/** 取消发行时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date cancel_dt;
	public Date getCancel_dt() {
		return cancel_dt;
	}
	public void setCancel_dt(Date cancel_dt) {
		this.cancel_dt = cancel_dt;
	}
	/** 停止发行时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date stop_dt;
	public Date getStop_dt() {
		return stop_dt;
	}
	public void setStop_dt(Date stop_dt) {
		this.stop_dt = stop_dt;
	}
	/** 发行状态 */
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
