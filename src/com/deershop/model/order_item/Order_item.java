package com.deershop.model.order_item;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Order_item {

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
	/** 订单ID */
	private  Long orders_id;
	public Long getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(Long orders_id) {
		this.orders_id = orders_id;
	}
	/** 商品ID */
	private  Long goods_id;
	public Long getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	/** 获得积分 */
	private  Long point;
	public Long getPoint() {
		return point;
	}
	public void setPoint(Long point) {
		this.point = point;
	}
	/** 使用折扣ID */
	private  Long discount_id;
	public Long getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(Long discount_id) {
		this.discount_id = discount_id;
	}
	/** 使用优惠券ID */
	private  Long promotion_id;
	public Long getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(Long promotion_id) {
		this.promotion_id = promotion_id;
	}
	/** 使用满减ID */
	private  Long full_id;
	public Long getFull_id() {
		return full_id;
	}
	public void setFull_id(Long full_id) {
		this.full_id = full_id;
	}
	/** 购买数量 */
	private  Long amount;
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	/** 商品单价 */
	private  String sell_price;
	public String getSell_price() {
		return sell_price;
	}
	public void setSell_price(String sell_price) {
		this.sell_price = sell_price;
	}
	/** 实付金额 */
	private  String real_price;
	public String getReal_price() {
		return real_price;
	}
	public void setReal_price(String real_price) {
		this.real_price = real_price;
	}
	/** 折扣金额 */
	private  String discount_price;
	public String getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(String discount_price) {
		this.discount_price = discount_price;
	}
	/** 满减金额 */
	private  String full_discount_price;
	public String getFull_discount_price() {
		return full_discount_price;
	}
	public void setFull_discount_price(String full_discount_price) {
		this.full_discount_price = full_discount_price;
	}
	/** 折让金额 */
	private  String concession_price;
	public String getConcession_price() {
		return concession_price;
	}
	public void setConcession_price(String concession_price) {
		this.concession_price = concession_price;
	}
	/** 优惠券金额 */
	private  String promotion_price;
	public String getPromotion_price() {
		return promotion_price;
	}
	public void setPromotion_price(String promotion_price) {
		this.promotion_price = promotion_price;
	}
	/** 是否发货状态 */
	private  String is_deliver;
	public String getIs_deliver() {
		return is_deliver;
	}
	public void setIs_deliver(String is_deliver) {
		this.is_deliver = is_deliver;
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

	
	/** 商品名称 */
	private  String goods_name;
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	/** 商品图片 */
	private  String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	/** 第一张图片 */
	private  String firstImg;
	public String getFirstImg() {
		return firstImg;
	}
	public void setFirstImg(String firstImg) {
		this.firstImg = firstImg;
	}
	
	private  String goods_num;
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}

}
