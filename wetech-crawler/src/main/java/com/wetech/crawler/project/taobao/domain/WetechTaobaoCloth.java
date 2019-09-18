package com.wetech.crawler.project.taobao.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 淘宝服装对象 wetech_taobao_cloth
 * 
 * @author Levin
 * @date 2019-09-16
 */
public class WetechTaobaoCloth implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 品牌名称 */
    private String shopTitle;

    /** 店铺名称 */
    private String shopLink;

    /** 服装链接 */
    private String clothLink;

    /** 服装ID */
    private String clothId;

    /** 服装标题 */
    private String clothTitle;

    /** 原价 */
    private Long clothPrice;

    /** 优惠价 */
    private Long clothSpecialPrice;

    /** 销售量 */
    private Long clothAmount;

    /** 上架时间 */
    private Date clothDate;

    /** 服装图片 */
    private String clothImage;

    /** 服装属性 */
    private String clothDesc;

    /** 服装标签 */
    private String clothTags;
    
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setShopTitle(String shopTitle) 
    {
        this.shopTitle = shopTitle;
    }

    public String getShopTitle() 
    {
        return shopTitle;
    }
    public void setShopLink(String shopLink) 
    {
        this.shopLink = shopLink;
    }

    public String getShopLink() 
    {
        return shopLink;
    }
    public void setClothLink(String clothLink) 
    {
        this.clothLink = clothLink;
    }

    public String getClothLink() 
    {
        return clothLink;
    }
    public void setClothId(String clothId) 
    {
        this.clothId = clothId;
    }

    public String getClothId() 
    {
        return clothId;
    }
    public void setClothTitle(String clothTitle) 
    {
        this.clothTitle = clothTitle;
    }

    public String getClothTitle() 
    {
        return clothTitle;
    }
    public void setClothPrice(Long clothPrice) 
    {
        this.clothPrice = clothPrice;
    }

    public Long getClothPrice() 
    {
        return clothPrice;
    }
    public void setClothSpecialPrice(Long clothSpecialPrice) 
    {
        this.clothSpecialPrice = clothSpecialPrice;
    }

    public Long getClothSpecialPrice() 
    {
        return clothSpecialPrice;
    }
    public void setClothAmount(Long clothAmount) 
    {
        this.clothAmount = clothAmount;
    }

    public Long getClothAmount() 
    {
        return clothAmount;
    }
    public void setClothDate(Date clothDate) 
    {
        this.clothDate = clothDate;
    }

    public Date getClothDate() 
    {
        return clothDate;
    }
    public void setClothImage(String clothImage) 
    {
        this.clothImage = clothImage;
    }

    public String getClothImage() 
    {
        return clothImage;
    }
    public void setClothDesc(String clothDesc) 
    {
        this.clothDesc = clothDesc;
    }

    public String getClothDesc() 
    {
        return clothDesc;
    }
    public void setClothTags(String clothTags) 
    {
        this.clothTags = clothTags;
    }

    public String getClothTags() 
    {
        return clothTags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopTitle", getShopTitle())
            .append("shopLink", getShopLink())
            .append("clothLink", getClothLink())
            .append("clothId", getClothId())
            .append("clothTitle", getClothTitle())
            .append("clothPrice", getClothPrice())
            .append("clothSpecialPrice", getClothSpecialPrice())
            .append("clothAmount", getClothAmount())
            .append("clothDate", getClothDate())
            .append("clothImage", getClothImage())
            .append("clothDesc", getClothDesc())
            .append("clothTags", getClothTags())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
