package com.plapsstudio.sumutte.model;

public class Movie {
	private String id, title, lokasi, thumbnailUrl;
	public Movie() {
	}

	public Movie(String name, String thumbnailUrl, String lokasi, String id) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.lokasi = lokasi;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
