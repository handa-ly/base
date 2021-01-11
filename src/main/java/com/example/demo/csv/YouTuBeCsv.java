package com.example.demo.csv;

import com.opencsv.bean.CsvBindByName;

public class YouTuBeCsv {

	@CsvBindByName(column="ID")
	private String resourceId;
	
	@CsvBindByName(column="TITLE")
	private String title;
	
	@CsvBindByName(column="ARTIST")
	private String artist;
	
	@CsvBindByName(column="ALBUM")
	private String album;
	
	@CsvBindByName(column="LABEL")
	private String label;
	
	@CsvBindByName(column="ISRC")
	private String isrc;
	
	@CsvBindByName(column="COMP_ID")
	private String compId;
	
	@CsvBindByName(column="COMP_TITLE")
	private String compTitle;
	
	@CsvBindByName(column="COMP_ISWC")
	private String compIswc;
	
	@CsvBindByName(column="COMP_WRITERS")
	private String compWriters;
	
	@CsvBindByName(column="COMP_CUSTOM_ID")
	private String compCustomId;
	
	@CsvBindByName(column="VIEWS")
	private String views;
	
	@CsvBindByName(column="QUANTILE")
	private String quantile;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
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

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getQuantile() {
		return quantile;
	}

	public void setQuantile(String quantile) {
		this.quantile = quantile;
	}

	@Override
	public String toString() {
		return "YouTuBeCsv{" +
				"resourceId='" + resourceId + '\'' +
				", title='" + title + '\'' +
				", artist='" + artist + '\'' +
				", album='" + album + '\'' +
				", label='" + label + '\'' +
				", isrc='" + isrc + '\'' +
				", compId='" + compId + '\'' +
				", compTitle='" + compTitle + '\'' +
				", compIswc='" + compIswc + '\'' +
				", compWriters='" + compWriters + '\'' +
				", compCustomId='" + compCustomId + '\'' +
				", views='" + views + '\'' +
				", quantile='" + quantile + '\'' +
				'}';
	}
}
