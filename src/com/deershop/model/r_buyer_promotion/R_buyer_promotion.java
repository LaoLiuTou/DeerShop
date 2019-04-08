package com.deershop.model.r_buyer_promotion;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class R_buyer_promotion {

	/** 默认主键 */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** 消费者ID */
	private  Long buyer_id;
	public Long getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(Long buyer_id) {
		this.buyer_id = buyer_id;
	}
	/** 优惠券ID */
	private  Long promotion_id;
	public Long getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(Long promotion_id) {
		this.promotion_id = promotion_id;
	}
	/** 领取类型 */
	private  String receive_type;
	public String getReceive_type() {
		return receive_type;
	}
	public void setReceive_type(String receive_type) {
		this.receive_type = receive_type;
	}
	/** 领取时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date receive_dt;
	public Date getReceive_dt() {
		return receive_dt;
	}
	public void setReceive_dt(Date receive_dt) {
		this.receive_dt = receive_dt;
	}
	/** 使用时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date use_dt;
	public Date getUse_dt() {
		return use_dt;
	}
	public void setUse_dt(Date use_dt) {
		this.use_dt = use_dt;
	}
	/** 使用状态 */
	private  String use_status;
	public String getUse_status() {
		return use_status;
	}
	public void setUse_status(String use_status) {
		this.use_status = use_status;
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
