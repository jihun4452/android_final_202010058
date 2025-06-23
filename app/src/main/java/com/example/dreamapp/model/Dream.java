package com.example.dreamapp.model;

import java.time.LocalDateTime;

import com.example.dreamapp.model.type.DreamType;

public class Dream {
	private final String title;
	private final String content;
	private final DreamType type;
	private final LocalDateTime createdAt;
	private final boolean showDescription;

	public Dream(String title, String content, DreamType type, LocalDateTime createdAt, boolean showDescription) {
		this.title = title;
		this.content = content;
		this.type = type;
		this.createdAt = createdAt;
		this.showDescription = showDescription;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public DreamType getType() {
		return type;
	}

	public boolean isShowDescription() {
		return showDescription;
	}
	public LocalDateTime getCreatedAt() { return createdAt; }
}