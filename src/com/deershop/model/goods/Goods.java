package com.deershop.model.goods;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Goods {

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
	/** 商品分类ID */
	private  Long ctg_id;
	public Long getCtg_id() {
		return ctg_id;
	}
	public void setCtg_id(Long ctg_id) {
		this.ctg_id = ctg_id;
	}
	/** 邮费方案ID */
	private  Long postage_id;
	public Long getPostage_id() {
		return postage_id;
	}
	public void setPostage_id(Long postage_id) {
		this.postage_id = postage_id;
	}
	/** 配送范围 */
	private  String delivery_rang;
	public String getDelivery_rang() {
		return delivery_rang;
	}
	public void setDelivery_rang(String delivery_rang) {
		this.delivery_rang = delivery_rang;
	}
	/** 原产品编号 */
	private  String old_prod_num;
	public String getOld_prod_num() {
		return old_prod_num;
	}
	public void setOld_prod_num(String old_prod_num) {
		this.old_prod_num = old_prod_num;
	}
	/** 编号 */
	private  String goods_num;
	
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
	/** 商品上架数量 */
	private  Long st_stock_amount;
	public Long getSt_stock_amount() {
		return st_stock_amount;
	}
	public void setSt_stock_amount(Long st_stock_amount) {
		this.st_stock_amount = st_stock_amount;
	}
	/** 商品库存数量 */
	private  Long new_stock_amount;
	public Long getNew_stock_amount() {
		return new_stock_amount;
	}
	public void setNew_stock_amount(Long new_stock_amount) {
		this.new_stock_amount = new_stock_amount;
	}
	/** 限购数量 */
	private  Long limit_amount;
	public Long getLimit_amount() {
		return limit_amount;
	}
	public void setLimit_amount(Long limit_amount) {
		this.limit_amount = limit_amount;
	}
	/** 商品描述信息 */
	private  String goods_desc;
	public String getGoods_desc() {
		return goods_desc;
	}
	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}
	/** 商品图片 */
	private  String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	/** 商品售价 */
	private  String sell_price;
	public String getSell_price() {
		return sell_price;
	}
	public void setSell_price(String sell_price) {
		this.sell_price = sell_price;
	}
	/** 是否可用优惠券 */
	private  String is_promotion;
	public String getIs_promotion() {
		return is_promotion;
	}
	public void setIs_promotion(String is_promotion) {
		this.is_promotion = is_promotion;
	}
	/** 是否上架 */
	private  String is_on_shelf;
	public String getIs_on_shelf() {
		return is_on_shelf;
	}
	public void setIs_on_shelf(String is_on_shelf) {
		this.is_on_shelf = is_on_shelf;
	}
	/** 是否主打 */
	private  String is_main;
	public String getIs_main() {
		return is_main;
	}
	public void setIs_main(String is_main) {
		this.is_main = is_main;
	}
	/** 是否热门 */
	private  String is_hot;
	public String getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(String is_hot) {
		this.is_hot = is_hot;
	}
	/** 上架时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date on_shelf_dt;
	public Date getOn_shelf_dt() {
		return on_shelf_dt;
	}
	public void setOn_shelf_dt(Date on_shelf_dt) {
		this.on_shelf_dt = on_shelf_dt;
	}
	/** 下架时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date under_shelf_dt;
	public Date getUnder_shelf_dt() {
		return under_shelf_dt;
	}
	public void setUnder_shelf_dt(Date under_shelf_dt) {
		this.under_shelf_dt = under_shelf_dt;
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

	/** 分类名称 */
	private  String ctg_name;
	public String getCtg_name() {
		return ctg_name;
	}
	public void setCtg_name(String ctg_name) {
		this.ctg_name = ctg_name;
	}
	/** 店铺名称 */
	private  String shop_name;
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	/** 方案名称 */
	private  String postage_name;
	public String getPostage_name() {
		return postage_name;
	}
	public void setPostage_name(String postage_name) {
		this.postage_name = postage_name;
	}
	/** 第一张图片 */
	private  String firstImg;
	public String getFirstImg() {
		return firstImg;
	}
	public void setFirstImg(String firstImg) {
		this.firstImg = firstImg;
	}
	

	private String pageviews;
	
	public String getPageviews() {
		return pageviews;
	}
	public void setPageviews(String pageviews) {
		this.pageviews = pageviews;
	}
	private Long sales;
	 
	public Long getSales() {
		return sales;
	}
	public void setSales(Long sales) {
		this.sales = sales;
	}
	
	private String goods_desc_img;
	public String getGoods_desc_img() {
		return goods_desc_img;
	}
	public void setGoods_desc_img(String goods_desc_img) {
		this.goods_desc_img = goods_desc_img;
	}
	
	private String is_limit_amount;
	public String getIs_limit_amount() {
		return is_limit_amount;
	}
	public void setIs_limit_amount(String is_limit_amount) {
		this.is_limit_amount = is_limit_amount;
	}
	private String par_ctg_name;
	public String getPar_ctg_name() {
		return par_ctg_name;
	}
	public void setPar_ctg_name(String par_ctg_name) {
		this.par_ctg_name = par_ctg_name;
	}
	
	
	
}
