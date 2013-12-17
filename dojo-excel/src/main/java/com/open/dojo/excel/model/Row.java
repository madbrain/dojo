package com.open.dojo.excel.model;

import java.util.ArrayList;
import java.util.List;

public class Row {

	private List<Cell> cells = new ArrayList<Cell>();
	private Table table;
	
	public Row(Table table, int size) {
		this.table = table;
		for (int i = 0; i < size; ++i) {
			addColumn();
		}
	}

	public int size() {
		return cells.size();
	}
	
	public int getIndex() {
		return table.getIndexOf(this);
	}
	
	public List<Cell> getCells() {
		return cells;
	}

	public int getCellIndex(Cell cell) {
		return cells.indexOf(cell);
	}

	public void addColumn() {
		this.cells.add(new Cell(this));
	}

	public void removeColumn(int index) {
		this.cells.remove(index);
	}

}
