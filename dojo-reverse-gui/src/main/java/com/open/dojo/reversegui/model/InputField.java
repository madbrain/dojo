package com.open.dojo.reversegui.model;

public abstract class InputField extends ScreenElement {

	private String id;

	public InputField(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
