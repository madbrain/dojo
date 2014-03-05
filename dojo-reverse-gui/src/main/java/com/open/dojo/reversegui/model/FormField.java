package com.open.dojo.reversegui.model;

import java.util.ArrayList;
import java.util.List;

public class FormField extends ScreenElement {

	private String label;
	private List<InputField> fields = new ArrayList<InputField>();

	public FormField(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public List<InputField> getFields() {
		return fields ;
	}

}
