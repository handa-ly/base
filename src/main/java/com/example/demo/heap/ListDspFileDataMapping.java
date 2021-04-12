package com.example.demo.heap;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`list_dsp_file_data_mapping`")
public class ListDspFileDataMapping  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 作品名称
     */
    @Column(name = "`title`")
    private String title;

    /**
     * 作品表演者(多个用；隔开)
     */
    @Column(name = "`work_artist`")
    private String workArtist;

    /**
     * 作词者(Lyricist\author)
     */
    @Column(name = "`author`")
    private String author;

    /**
     * 作曲者
     */
    @Column(name = "`composer`")
    private String composer;

    /**
     * 剧集
     */
    @Column(name = "`episode_no`")
    private String episodeNo;

    /**
     * 作品类型
     */
    @Column(name = "`work_type`")
    private String workType;

    /**
     * 使用次數
     */
    @Column(name = "`click_number`")
    private BigDecimal clickNumber;

    /**
     * 免費的使用次數
     */
    @Column(name = "`free_click_number`")
    private BigDecimal freeClickNumber;

    /**
     * isrc
     */
    @Column(name = "`isrc`")
    private String isrc;

    /**
     * iswc
     */
    @Column(name = "`iswc`")
    private String iswc;

    /**
     * 使用类型(PermanentDownload\
     */
    @Column(name = "`usage`")
    private String usage;

    /**
     * 时长
     */
    @Column(name = "`duration_str`")
    private String durationStr;

    /**
     * 时长-m(分钟)
     */
    @Column(name = "`duration_m`")
    private Integer durationM;

    /**
     * 时长-s（秒）
     */
    @Column(name = "`duration_s`")
    private Integer durationS;

    /**
     * 使用区域
     */
    @Column(name = "`use_area`")
    private String useArea;

    /**
     * 清单文件开始日期
     */
    @Column(name = "`list_file_start_time`")
    private Date listFileStartTime;

    /**
     * 清单文件结束日期
     */
    @Column(name = "`list_file_end_time`")
    private Date listFileEndTime;

    /**
     * 销售量
     */
    @Column(name = "`sales_number`")
    private BigDecimal salesNumber;

    /**
     * 作品来源
     */
    @Column(name = "`work_source`")
    private String workSource;

    /**
     * 作品价格
     */
    @Column(name = "`work_price`")
    private BigDecimal workPrice;

    /**
     * 清单里数据的使用日期
     */
    @Column(name = "`usage_time`")
    private Date usageTime;

    /**
     * list_file_data的爸爸--list_file可以有多个--用于拉单
     */
    @Column(name = "`list_parent_md5`")
    private String listParentMd5;

    /**
     * 用于分组---针对ddex的主作品
     */
    @Column(name = "`group_code`")
    private String groupCode;

    /**
     * 类型(VideoSingle\Album\Single)
     */
    @Column(name = "`release_type`")
    private String releaseType;

    /**
     * 版权商
     */
    @Column(name = "`publisher`")
    private String publisher;

    /**
     * 分配所用到的比率如果click_num没有就用price
     */
    @Column(name = "`dist_ratio`")
    private BigDecimal distRatio;

    /**
     * 公司
     */
    @Column(name = "`company`")
    private String company;

    /**
     * 业务id
     */
    @Column(name = "`business_id`")
    private String businessId;

    /**
     * 对应ccid文档的resourceId
     */
    @Column(name = "`resource_id`")
    private String resourceId;

    /**
     * 对应ccid文档releaseId
     */
    @Column(name = "`release_id`")
    private String releaseId;

    /**
     * ccid产品名称
     */
    @Column(name = "`product`")
    private String product;

    /**
     * 专辑标题
     */
    @Column(name = "`album_title`")
    private String albumTitle;

    /**
     * 歌曲占比，单首 100.00 ,打包的歌曲，根据时长计算在打包里的占比
     */
    @Column(name = "`music_share`")
    private BigDecimal musicShare;
    
    @Column(name = "`label`")
	private String label;
	
    @Column(name = "`comp_id`")
	private String compId;
	
    @Column(name = "`comp_title`")
	private String compTitle;
    
    @Column(name = "`comp_iswc`")
	private String compIswc;
	
    @Column(name = "`comp_writers`")
    private String compWriters;
    
    @Column(name = "`comp_custom_id`")
	private String compCustomId;
    
    @Column(name = "`views`")
	private Long views;
    
    @Column(name = "`quantile`")
	private Long quantile;
    
    @Column(name="file_base_id")
    private Long fileBaseId;
    
    @Column(name="status")
    private Integer status;
    
    @Column(name = "commercial_model")
    private String commercialModel;

    @Column(name = "ext_json")
    private String extJson;


    @Column(name = "per_share")
    private BigDecimal perShare;

    @Column(name = "mec_share")
    private BigDecimal mecShare;

    @Column(name="data_unique_key")
    private String dataUniqueKey;

    @Column(name="data_unique_key_str")
    private String dataUniqueKeyStr;

    // 用于存储mwTitle的数据 多个;分割  前面这个地方是放在title中的， 因MUST要求 title中放置的是AS01.01的title
    @Column(name="mw_title")
    private String mwTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkArtist() {
        return workArtist;
    }

    public void setWorkArtist(String workArtist) {
        this.workArtist = workArtist;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(String episodeNo) {
        this.episodeNo = episodeNo;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public BigDecimal getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(BigDecimal clickNumber) {
        this.clickNumber = clickNumber;
    }

    public BigDecimal getFreeClickNumber() {
        return freeClickNumber;
    }

    public void setFreeClickNumber(BigDecimal freeClickNumber) {
        this.freeClickNumber = freeClickNumber;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getIswc() {
        return iswc;
    }

    public void setIswc(String iswc) {
        this.iswc = iswc;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    public Integer getDurationM() {
        return durationM;
    }

    public void setDurationM(Integer durationM) {
        this.durationM = durationM;
    }

    public Integer getDurationS() {
        return durationS;
    }

    public void setDurationS(Integer durationS) {
        this.durationS = durationS;
    }

    public String getUseArea() {
        return useArea;
    }

    public void setUseArea(String useArea) {
        this.useArea = useArea;
    }

    public Date getListFileStartTime() {
        return listFileStartTime;
    }

    public void setListFileStartTime(Date listFileStartTime) {
        this.listFileStartTime = listFileStartTime;
    }

    public Date getListFileEndTime() {
        return listFileEndTime;
    }

    public void setListFileEndTime(Date listFileEndTime) {
        this.listFileEndTime = listFileEndTime;
    }

    public BigDecimal getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(BigDecimal salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getWorkSource() {
        return workSource;
    }

    public void setWorkSource(String workSource) {
        this.workSource = workSource;
    }

    public BigDecimal getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(BigDecimal workPrice) {
        this.workPrice = workPrice;
    }

    public Date getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(Date usageTime) {
        this.usageTime = usageTime;
    }

    public String getListParentMd5() {
        return listParentMd5;
    }

    public void setListParentMd5(String listParentMd5) {
        this.listParentMd5 = listParentMd5;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BigDecimal getDistRatio() {
        return distRatio;
    }

    public void setDistRatio(BigDecimal distRatio) {
        this.distRatio = distRatio;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public BigDecimal getMusicShare() {
        return musicShare;
    }

    public void setMusicShare(BigDecimal musicShare) {
        this.musicShare = musicShare;
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCompTitle() {
		return compTitle;
	}

	public void setCompTitle(String compTitle) {
		this.compTitle = compTitle;
	}

	public String getCompIswc() {
		return compIswc;
	}

	public void setCompIswc(String compIswc) {
		this.compIswc = compIswc;
	}

	public String getCompWriters() {
		return compWriters;
	}

	public void setCompWriters(String compWriters) {
		this.compWriters = compWriters;
	}

	public String getCompCustomId() {
		return compCustomId;
	}

	public void setCompCustomId(String compCustomId) {
		this.compCustomId = compCustomId;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public Long getQuantile() {
		return quantile;
	}

	public void setQuantile(Long quantile) {
		this.quantile = quantile;
	}

	public Long getFileBaseId() {
		return fileBaseId;
	}

	public void setFileBaseId(Long fileBaseId) {
		this.fileBaseId = fileBaseId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCommercialModel() {
		return commercialModel;
	}

	public void setCommercialModel(String commercialModel) {
		this.commercialModel = commercialModel;
	}

    public String getExtJson() {
        return extJson;
    }

    public void setExtJson(String extJson) {
        this.extJson = extJson;
    }



    public BigDecimal getPerShare() {
        return perShare;
    }

    public void setPerShare(BigDecimal perShare) {
        this.perShare = perShare;
    }

    public BigDecimal getMecShare() {
        return mecShare;
    }

    public void setMecShare(BigDecimal mecShare) {
        this.mecShare = mecShare;
    }

    public String getDataUniqueKey() {
        return dataUniqueKey;
    }

    public void setDataUniqueKey(String dataUniqueKey) {
        this.dataUniqueKey = dataUniqueKey;
    }

    public String getDataUniqueKeyStr() {
        return dataUniqueKeyStr;
    }

    public void setDataUniqueKeyStr(String dataUniqueKeyStr) {
        this.dataUniqueKeyStr = dataUniqueKeyStr;
    }

    public String getMwTitle() {
        return mwTitle;
    }

    public void setMwTitle(String mwTitle) {
        this.mwTitle = mwTitle;
    }

    @Override
    public String toString() {
        return clickNumber + "";
    }
}