package com.open.dojo.reversegui.model;

import java.util.ArrayList;
import java.util.List;

public class GroupBox extends ScreenElement {

	private String label;
	private List<ScreenElement> elements = new ArrayList<ScreenElement>();

	public GroupBox(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public List<ScreenElement> getElements() {
		return elements;
	}

}
