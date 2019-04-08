package com.deershop.model.postage;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Postage {

	/** 默认主键 */
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
	/** 方案名称 */
	private  String postage_name;
	public String getPostage_name() {
		return postage_name;
	}
	public void setPostage_name(String postage_name) {
		this.postage_name = postage_name;
	}
	/** 方案描述 */
	private  String postage_desc;
	public String getPostage_desc() {
		return postage_desc;
	}
	public void setPostage_desc(String postage_desc) {
		this.postage_desc = postage_desc;
	}
	/** 规则 */
	private  String rule;
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	/** 是否启用 */
	private  String is_use;
	public String getIs_use() {
		return is_use;
	}
	public void setIs_use(String is_use) {
		this.is_use = is_use;
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

	private String postagecol;
	public String getPostagecol() {
		return postagecol;
	}
	public void setPostagecol(String postagecol) {
		this.postagecol = postagecol;
	}


}
