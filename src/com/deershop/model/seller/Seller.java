package com.deershop.model.seller;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Seller {

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
	/** 原系统商家编号 */
	private  String old_seller_num;
	public String getOld_seller_num() {
		return old_seller_num;
	}
	public void setOld_seller_num(String old_seller_num) {
		this.old_seller_num = old_seller_num;
	}
	/** 店铺名称 */
	private  String shop_name;
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	/** 店铺介绍 */
	private  String shop_desc;
	public String getShop_desc() {
		return shop_desc;
	}
	public void setShop_desc(String shop_desc) {
		this.shop_desc = shop_desc;
	}
	/** 店铺联系电话 */
	private  String shop_tel;
	public String getShop_tel() {
		return shop_tel;
	}
	public void setShop_tel(String shop_tel) {
		this.shop_tel = shop_tel;
	}
	/** 密码 */
	private  String pwd;
	 
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/** 店铺地址 */
	private  String shop_addr;
	public String getShop_addr() {
		return shop_addr;
	}
	public void setShop_addr(String shop_addr) {
		this.shop_addr = shop_addr;
	}
	/** 店铺图标 */
	private  String shop_img;
	public String getShop_img() {
		return shop_img;
	}
	public void setShop_img(String shop_img) {
		this.shop_img = shop_img;
	}
	/** 退货信息 */
	private  String return_info;
	public String getReturn_info() {
		return return_info;
	}
	public void setReturn_info(String return_info) {
		this.return_info = return_info;
	}
	/** 总收入 */
	private  String all_income;
	public String getAll_income() {
		return all_income;
	}
	public void setAll_income(String all_income) {
		this.all_income = all_income;
	}
	/** 未结算收入 */
	private  String no_clear_income;
	public String getNo_clear_income() {
		return no_clear_income;
	}
	public void setNo_clear_income(String no_clear_income) {
		this.no_clear_income = no_clear_income;
	}
	/** 已提现金额 */
	private  String cash;
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	/**可提现金额*/
	private String can_cash;
	public String getCan_cash() {
		return can_cash;
	}
	public void setCan_cash(String can_cash) {
		this.can_cash = can_cash;
	}
	/**提现中金额*/
	private String cashing;
	public String getCashing() {
		return cashing;
	}
	public void setCashing(String cashing) {
		this.cashing = cashing;
	}
	/** 保证金 */
	private  String caution_money;
	public String getCaution_money() {
		return caution_money;
	}
	public void setCaution_money(String caution_money) {
		this.caution_money = caution_money;
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

	/**审核人ID*/
	private Long audit_mem_id;
	public Long getAudit_mem_id() {
		return audit_mem_id;
	}
	public void setAudit_mem_id(Long audit_mem_id) {
		this.audit_mem_id = audit_mem_id;
	}
	/**审核状态*/
	private String audit_status;
	public String getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}
	/**是否订单通知(1通知/0不通知)*/
	private String is_order_notice;
	public String getIs_order_notice() {
		return is_order_notice;
	}
	public void setIs_order_notice(String is_order_notice) {
		this.is_order_notice = is_order_notice;
	}
	private String business_license;
	public String getBusiness_license() {
		return business_license;
	}
	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}
	private String reject_reason;
	public String getReject_reason() {
		return reject_reason;
	}
	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}
	
	private Long seller_card_id;
	public Long getSeller_card_id() {
		return seller_card_id;
	}
	public void setSeller_card_id(Long seller_card_id) {
		this.seller_card_id = seller_card_id;
	}
	
	private String wechat;
	private String openid;
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
}
