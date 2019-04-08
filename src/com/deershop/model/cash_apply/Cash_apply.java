package com.deershop.model.cash_apply;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Cash_apply {

	/** ID */
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
	/** 总金额=提现金额+提现收费 */
	private  String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	/** 提现渠道 */
	private  String cash_type;
	public String getCash_type() {
		return cash_type;
	}
	public void setCash_type(String cash_type) {
		this.cash_type = cash_type;
	}
	/** 提现信息 */
	private  String cash_info;
	public String getCash_info() {
		return cash_info;
	}
	public void setCash_info(String cash_info) {
		this.cash_info = cash_info;
	}
	/** 提现状态 */
	private  String cash_status;
	public String getCash_status() {
		return cash_status;
	}
	public void setCash_status(String cash_status) {
		this.cash_status = cash_status;
	}
	/** 提现金额 */
	private  String cash_price;
	public String getCash_price() {
		return cash_price;
	}
	public void setCash_price(String cash_price) {
		this.cash_price = cash_price;
	}
	/** 提现收费 */
	private  String cash_service;
	public String getCash_service() {
		return cash_service;
	}
	public void setCash_service(String cash_service) {
		this.cash_service = cash_service;
	}
	/** 提现费率 */
	private  String cash_rate;
	public String getCash_rate() {
		return cash_rate;
	}
	public void setCash_rate(String cash_rate) {
		this.cash_rate = cash_rate;
	}
	/** 提现申请流水号 */
	private  String cash_code;
	public String getCash_code() {
		return cash_code;
	}
	public void setCash_code(String cash_code) {
		this.cash_code = cash_code;
	}
	/** 提现处理人 */
	private  Long cash_deal_id;
	public Long getCash_deal_id() {
		return cash_deal_id;
	}
	public void setCash_deal_id(Long cash_deal_id) {
		this.cash_deal_id = cash_deal_id;
	}
	/** 提现发起时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date cash_st_dt;
	public Date getCash_st_dt() {
		return cash_st_dt;
	}
	public void setCash_st_dt(Date cash_st_dt) {
		this.cash_st_dt = cash_st_dt;
	}
	/** 提现成功时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date cash_ok_dt;
	public Date getCash_ok_dt() {
		return cash_ok_dt;
	}
	public void setCash_ok_dt(Date cash_ok_dt) {
		this.cash_ok_dt = cash_ok_dt;
	}
	/** 处理时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date deal_date;
	public Date getDeal_date() {
		return deal_date;
	}
	public void setDeal_date(Date deal_date) {
		this.deal_date = deal_date;
	}
	/** 店铺名称 */
	private  String shop_name;
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	/** 店铺联系电话 */
	private  String shop_tel;
	public String getShop_tel() {
		return shop_tel;
	}
	public void setShop_tel(String shop_tel) {
		this.shop_tel = shop_tel;
	}
	/** 凭证图片 */
	private  String cash_img;
	public String getCash_img() {
		return cash_img;
	}
	public void setCash_img(String cash_img) {
		this.cash_img = cash_img;
	}

}
