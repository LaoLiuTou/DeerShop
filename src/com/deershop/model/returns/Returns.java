package com.deershop.model.returns;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Returns {

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
	/** 商家ID */
	private  Long seller_id;
	public Long getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(Long seller_id) {
		this.seller_id = seller_id;
	}
	/** 订单ID */
	private  Long orders_id;
	public Long getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(Long orders_id) {
		this.orders_id = orders_id;
	}
	/** 子订单ID串 */
	private  String order_item_ids;
	 
	public String getOrder_item_ids() {
		return order_item_ids;
	}
	public void setOrder_item_ids(String order_item_ids) {
		this.order_item_ids = order_item_ids;
	}
	/** 发起方 */
	private  String initiator;
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	/** 退货原因分类 */
	private  String return_type;
	public String getReturn_type() {
		return return_type;
	}
	public void setReturn_type(String return_type) {
		this.return_type = return_type;
	}
	/** 退货图片 */
	private  String return_img;
	public String getReturn_img() {
		return return_img;
	}
	public void setReturn_img(String return_img) {
		this.return_img = return_img;
	}
	/** 问题描述 */
	private  String problem;
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	/** 发货方式 */
	private  String send_type;
	public String getSend_type() {
		return send_type;
	}
	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}
	/** 退款金额 */
	private  String return_price;
	public String getReturn_price() {
		return return_price;
	}
	public void setReturn_price(String return_price) {
		this.return_price = return_price;
	}
	/** 快递公司 */
	private  String express;
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	/** 单号 */
	private  String express_code;
	public String getExpress_code() {
		return express_code;
	}
	public void setExpress_code(String express_code) {
		this.express_code = express_code;
	}
	/** 确认方是否同意 */
	private  String is_agree;
	public String getIs_agree() {
		return is_agree;
	}
	public void setIs_agree(String is_agree) {
		this.is_agree = is_agree;
	}
	/** 是否已发货 */
	private  String is_send;
	public String getIs_send() {
		return is_send;
	}
	public void setIs_send(String is_send) {
		this.is_send = is_send;
	}
	/** 是否确认收货 */
	private  String is_take;
	public String getIs_take() {
		return is_take;
	}
	public void setIs_take(String is_take) {
		this.is_take = is_take;
	}
	/** 退款是否到达 */
	private  String is_return;
	public String getIs_return() {
		return is_return;
	}
	public void setIs_return(String is_return) {
		this.is_return = is_return;
	}
	/** 发起者提交申请时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date submit_dt;
	public Date getSubmit_dt() {
		return submit_dt;
	}
	public void setSubmit_dt(Date submit_dt) {
		this.submit_dt = submit_dt;
	}
	/** 是否同意时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date agree_dt;
	public Date getAgree_dt() {
		return agree_dt;
	}
	public void setAgree_dt(Date agree_dt) {
		this.agree_dt = agree_dt;
	}
	/** 发货时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date send_dt;
	public Date getSend_dt() {
		return send_dt;
	}
	public void setSend_dt(Date send_dt) {
		this.send_dt = send_dt;
	}
	/** 确认收货时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date take_dt;
	public Date getTake_dt() {
		return take_dt;
	}
	public void setTake_dt(Date take_dt) {
		this.take_dt = take_dt;
	}
	/** 退款到达时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date return_dt;
	public Date getReturn_dt() {
		return return_dt;
	}
	public void setReturn_dt(Date return_dt) {
		this.return_dt = return_dt;
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


	
	/** 店铺名称 */
	private  String shop_name;
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	/** 商品编号 */
	private String goods_num;
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
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
	/** 退货数量 */
	private Long amount;
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	private String return_code;
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	
	private String order_num;
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	

	private String return_ctg;
	public String getReturn_ctg() {
		return return_ctg;
	}
	public void setReturn_ctg(String return_ctg) {
		this.return_ctg = return_ctg;
	}
	private String timediff;
	public String getTimediff() {
		return timediff;
	}
	public void setTimediff(String timediff) {
		this.timediff = timediff;
	}
	
	private String refuse_reason;
	private String refuse_img;
	public String getRefuse_reason() {
		return refuse_reason;
	}
	public void setRefuse_reason(String refuse_reason) {
		this.refuse_reason = refuse_reason;
	}
	public String getRefuse_img() {
		return refuse_img;
	}
	public void setRefuse_img(String refuse_img) {
		this.refuse_img = refuse_img;
	}
	
	 
}
