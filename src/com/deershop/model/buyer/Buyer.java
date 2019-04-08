package com.deershop.model.buyer;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Buyer {

	/** 默认主键 */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** 创建者ID */
	private  Long c_id;
	public Long getC_id() {
		return c_id;
	}
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	/** 主收货地址ID */
	private  Long main_addr_id;
	public Long getMain_addr_id() {
		return main_addr_id;
	}
	public void setMain_addr_id(Long main_addr_id) {
		this.main_addr_id = main_addr_id;
	}
	/** 微信号 */
	private  String wechat;
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	/** 积分 */
	private  Long point;
	public Long getPoint() {
		return point;
	}
	public void setPoint(Long point) {
		this.point = point;
	}
	/** 头像 */
	private  String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	/** 最后登录时间？ */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date last_login_dt;
	public Date getLast_login_dt() {
		return last_login_dt;
	}
	public void setLast_login_dt(Date last_login_dt) {
		this.last_login_dt = last_login_dt;
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
