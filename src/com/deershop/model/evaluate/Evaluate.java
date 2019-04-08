package com.deershop.model.evaluate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Evaluate {

	/** 默认主键 */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** 上级评价ID */
	private  Long pid;
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/** 消费者ID */
	private  Long buyer_id;
	public Long getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(Long buyer_id) {
		this.buyer_id = buyer_id;
	}
	/** 商品ID */
	private  Long goods_id;
	public Long getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	/** 评价内容/商家回复 */
	private  String evaluate_comment;
	public String getEvaluate_comment() {
		return evaluate_comment;
	}
	public void setEvaluate_comment(String evaluate_comment) {
		this.evaluate_comment = evaluate_comment;
	}
	/** 评价图片 */
	private  String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	/** 综合评分 */
	private  Long all_score;
	public Long getAll_score() {
		return all_score;
	}
	public void setAll_score(Long all_score) {
		this.all_score = all_score;
	}
	/** 快递评分 */
	private  Long express_score;
	public Long getExpress_score() {
		return express_score;
	}
	public void setExpress_score(Long express_score) {
		this.express_score = express_score;
	}
	/** 商品评分 */
	private  Long goods_score;
	public Long getGoods_score() {
		return goods_score;
	}
	public void setGoods_score(Long goods_score) {
		this.goods_score = goods_score;
	}
	/** 评价时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date evaluate_dt;
	public Date getEvaluate_dt() {
		return evaluate_dt;
	}
	public void setEvaluate_dt(Date evaluate_dt) {
		this.evaluate_dt = evaluate_dt;
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
