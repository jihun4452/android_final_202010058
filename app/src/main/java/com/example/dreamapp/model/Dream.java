package com.example.dreamapp.model;

import com.example.dreamapp.model.type.DreamType;

public class Dream {
	private final String title;
	private final String content;
	private final DreamType type;
	private final boolean showDescription;

	public Dream(String title, String content, DreamType type, boolean showDescription) {
		this.title = title;
		this.content = content;
		this.type = type;
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
}