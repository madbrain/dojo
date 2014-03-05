package com.open.dojo.reversegui;


public class Widget {

	private WidgetType type;
	private String name;
	private Rectangle bounds;

	public Widget(WidgetType type, String name, Rectangle bounds) {
		this.type = type;
		this.name = name;
		this.bounds = bounds;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public WidgetType getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
}
