package com.d103.newreka.hottopic.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Document(indexName = "news-*")
@Getter
@Setter
public class NewsDocument {

	@Id
	private String id;

	@Field(type = FieldType.Text)
	private String category;

	@Field(type = FieldType.Text)
	private String content;

	@Field(type = FieldType.Text)
	private String imgUrl;

	@Field(type = FieldType.Text)
	private String newsComp;

	@Field(type = FieldType.Date, format=DateFormat.custom, pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;

	@Field(type = FieldType.Text)
	private String title;

	@Field(type = FieldType.Text)
	private String url;

	@Override
	public String toString() {
		return "NewsDocument{" +
			"id='" + id + '\'' +
			", category='" + category + '\'' +
			", content='" + content + '\'' +
			", imgUrl='" + imgUrl + '\'' +
			", newsComp='" + newsComp + '\'' +
			", time=" + time +
			", title='" + title + '\'' +
			", url='" + url + '\'' +
			'}';
	}
}
