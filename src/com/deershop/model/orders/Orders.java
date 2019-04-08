package com.deershop.model.orders;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.deershop.model.order_item.Order_item;
/**
 * @author LT
 */
public class Orders {

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
	/** 商家ID */
	private  Long seller_id;
	public Long getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(Long seller_id) {
		this.seller_id = seller_id;
	}
	/** 消费者ID */
	private  Long buyer_id;
	public Long getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(Long buyer_id) {
		this.buyer_id = buyer_id;
	}
	
	
	/** 快递单号 */
	private  String express_code;
	public String getExpress_code() {
		return express_code;
	}
	public void setExpress_code(String express_code) {
		this.express_code = express_code;
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
	
	private Long sheng_id;
	private Long shi_id;
	private Long qu_id;
	
	public Long getSheng_id() {
		return sheng_id;
	}
	public void setSheng_id(Long sheng_id) {
		this.sheng_id = sheng_id;
	}
	public Long getShi_id() {
		return shi_id;
	}
	public void setShi_id(Long shi_id) {
		this.shi_id = shi_id;
	}
	public Long getQu_id() {
		return qu_id;
	}
	public void setQu_id(Long qu_id) {
		this.qu_id = qu_id;
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
	
	private String sheng;
	private String shi;
	private String qu;
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getQu() {
		return qu;
	}
	public void setQu(String qu) {
		this.qu = qu;
	}
	private String addr;
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/** 订单总金额 */
	private  String all_price;
	public String getAll_price() {
		return all_price;
	}
	public void setAll_price(String all_price) {
		this.all_price = all_price;
	}
	/** 订单商品总金额 */
	private  String all_goods_price;
	public String getAll_goods_price() {
		return all_goods_price;
	}
	public void setAll_goods_price(String all_goods_price) {
		this.all_goods_price = all_goods_price;
	}
	/** 邮费 */
	private  String postage_price;
	public String getPostage_price() {
		return postage_price;
	}
	public void setPostage_price(String postage_price) {
		this.postage_price = postage_price;
	}
	/** 优惠券金额 */
	private  String promotion_price;
	public String getPromotion_price() {
		return promotion_price;
	}
	public void setPromotion_price(String promotion_price) {
		this.promotion_price = promotion_price;
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
	/** 实付金额 */
	private  String real_price;
	public String getReal_price() {
		return real_price;
	}
	public void setReal_price(String real_price) {
		this.real_price = real_price;
	}
	/** 获得积分 */
	private  Long point;
	public Long getPoint() {
		return point;
	}
	public void setPoint(Long point) {
		this.point = point;
	}
	/** 付款方式 */
	private  String pay_type;
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	/** 详细付款信息 */
	private  String pay_info;
	public String getPay_info() {
		return pay_info;
	}
	public void setPay_info(String pay_info) {
		this.pay_info = pay_info;
	}
	/** 是否付款 */
	private  String is_pay;
	public String getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(String is_pay) {
		this.is_pay = is_pay;
	}
	/** 是否拆分发货 */
	private  String is_split;
	public String getIs_split() {
		return is_split;
	}
	public void setIs_split(String is_split) {
		this.is_split = is_split;
	}
	/** 是否发货 */
	private  String is_deliver;
	public String getIs_deliver() {
		return is_deliver;
	}
	public void setIs_deliver(String is_deliver) {
		this.is_deliver = is_deliver;
	}
	/** 是否确认收货 */
	private  String is_confirm;
	public String getIs_confirm() {
		return is_confirm;
	}
	public void setIs_confirm(String is_confirm) {
		this.is_confirm = is_confirm;
	}
	/** 是否评价 */
	private  String is_evaluate;
	public String getIs_evaluate() {
		return is_evaluate;
	}
	public void setIs_evaluate(String is_evaluate) {
		this.is_evaluate = is_evaluate;
	}
	
	/** 是否取消 */
	private  String is_cancel;
	public String getIs_cancel() {
		return is_cancel;
	}
	public void setIs_cancel(String is_cancel) {
		this.is_cancel = is_cancel;
	}
	/** 是否关闭 */
	private  String is_close;
	public String getIs_close() {
		return is_close;
	}
	public void setIs_close(String is_close) {
		this.is_close = is_close;
	}
	/** 是否删除 */
	private  String is_delete;
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	/** 订单创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date create_dt;
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	/** 付款时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date pay_dt;
	public Date getPay_dt() {
		return pay_dt;
	}
	public void setPay_dt(Date pay_dt) {
		this.pay_dt = pay_dt;
	}
	/** 订单完成时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date complete_dt;
	public Date getComplete_dt() {
		return complete_dt;
	}
	public void setComplete_dt(Date complete_dt) {
		this.complete_dt = complete_dt;
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
	/** 订单编号 */
	private  String order_num;
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	
	private String buyer_remark;
	
	private String seller_remark;
	public String getBuyer_remark() {
		return buyer_remark;
	}
	public void setBuyer_remark(String buyer_remark) {
		this.buyer_remark = buyer_remark;
	}
	public String getSeller_remark() {
		return seller_remark;
	}
	public void setSeller_remark(String seller_remark) {
		this.seller_remark = seller_remark;
	}	
	/** 子订单*/
	private List<Order_item> order_item;
	public List<Order_item> getOrder_item() {
		return order_item;
	}
	public void setOrder_item(List<Order_item> order_item) {
		this.order_item = order_item;
	}
	
	private String order_status;
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	private String timediff;
	public String getTimediff() {
		return timediff;
	}
	public void setTimediff(String timediff) {
		this.timediff = timediff;
	}
	
	private String express_name;
	public String getExpress_name() {
		return express_name;
	}
	public void setExpress_name(String express_name) {
		this.express_name = express_name;
	}
	
	private String is_delete_app;
	public String getIs_delete_app() {
		return is_delete_app;
	}
	public void setIs_delete_app(String is_delete_app) {
		this.is_delete_app = is_delete_app;
	}
	

}
