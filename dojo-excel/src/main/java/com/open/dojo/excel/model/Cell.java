package com.open.dojo.excel.model;

public class Cell {

	private Row row;
	private String value = "";

	public Cell(Row row) {
		this.row = row;
	}

	public String getPosition() {
		return row.getIndex() + "-" + row.getCellIndex(this);
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String newValue) {
		this.value = newValue;
	}
}
