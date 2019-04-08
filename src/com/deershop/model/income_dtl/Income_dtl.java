package com.deershop.model.income_dtl;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Income_dtl {

	/** ID */
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
	/** 商家ID */
	private  Long seller_id;
	public Long getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(Long seller_id) {
		this.seller_id = seller_id;
	}
	/** 订单ID/退换ID/提现ID */
	private  Long orders_id;
	public Long getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(Long orders_id) {
		this.orders_id = orders_id;
	}
	/** 分类 */
	private  String income_type;
	public String getIncome_type() {
		return income_type;
	}
	public void setIncome_type(String income_type) {
		this.income_type = income_type;
	}
	/** 项目 */
	private  String income_item;
	public String getIncome_item() {
		return income_item;
	}
	public void setIncome_item(String income_item) {
		this.income_item = income_item;
	}
	/** 金额 */
	private  String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	/** 收入支出时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date operate_dt;
	public Date getOperate_dt() {
		return operate_dt;
	}
	public void setOperate_dt(Date operate_dt) {
		this.operate_dt = operate_dt;
	}

	private String num;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	private String bankinfo;
	public String getBankinfo() {
		return bankinfo;
	}
	public void setBankinfo(String bankinfo) {
		this.bankinfo = bankinfo;
	}
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	


}
