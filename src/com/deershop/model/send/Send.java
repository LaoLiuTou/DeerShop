package com.deershop.model.send;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Send {

	/** 默认主键 */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** 订单ID */
	private  Long orders_id;
	public Long getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(Long orders_id) {
		this.orders_id = orders_id;
	}
	/** 包裹号 */
	private  Long package_num;
	public Long getPackage_num() {
		return package_num;
	}
	public void setPackage_num(Long package_num) {
		this.package_num = package_num;
	}
	/** 子订单ID串 */
	private  String order_item_ids;
	public String getOrder_item_ids() {
		return order_item_ids;
	}
	public void setOrder_item_ids(String order_item_ids) {
		this.order_item_ids = order_item_ids;
	}
	/** 发货方式 */
	private  String send_type;
	public String getSend_type() {
		return send_type;
	}
	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}
	/** 快递公司 */
	private  String express_name;
	public String getExpress_name() {
		return express_name;
	}
	public void setExpress_name(String express_name) {
		this.express_name = express_name;
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
