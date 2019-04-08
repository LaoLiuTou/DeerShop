package com.deershop.model.buyer_addr;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Buyer_addr {

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
	/** 消费者ID */
	private  Long buyer_id;
	public Long getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(Long buyer_id) {
		this.buyer_id = buyer_id;
	}
	/** 省id */
	private  Long sheng_id;
	public Long getSheng_id() {
		return sheng_id;
	}
	public void setSheng_id(Long sheng_id) {
		this.sheng_id = sheng_id;
	}
	/** 市id */
	private  Long shi_id;
	public Long getShi_id() {
		return shi_id;
	}
	public void setShi_id(Long shi_id) {
		this.shi_id = shi_id;
	}
	/** 区id */
	private  Long qu_id;
	public Long getQu_id() {
		return qu_id;
	}
	public void setQu_id(Long qu_id) {
		this.qu_id = qu_id;
	}
	/** 详细地址 */
	private  String addr;
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/** 收货人 */
	private  String receiver;
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	/** 联系电话 */
	private  String receiver_tel;
	public String getReceiver_tel() {
		return receiver_tel;
	}
	public void setReceiver_tel(String receiver_tel) {
		this.receiver_tel = receiver_tel;
	}
	/** 是否主地址 */
	private  String is_main_addr;
	public String getIs_main_addr() {
		return is_main_addr;
	}
	public void setIs_main_addr(String is_main_addr) {
		this.is_main_addr = is_main_addr;
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
