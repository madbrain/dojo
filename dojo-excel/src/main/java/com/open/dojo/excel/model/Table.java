package com.open.dojo.excel.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table {
	
	private int width = 2;
	private List<Row> rows = new ArrayList<Row>();
	
	public Table() {
		addRow();
		addRow();
	}
	
	private class ColumnIterator implements Iterator<Integer> {
		
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < width;
		}

		@Override
		public Integer next() {
			return index++;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

	public Iterator<Integer> getColumns() {
		return new ColumnIterator();
	}
	
	public List<Row> getRows() {
		return rows;
	}

	public int getIndexOf(Row row) {
		return rows.indexOf(row);
	}

	public void addCoumn() {
		for (Row row : rows) {
			row.addColumn();
		}
		++width;
	}
	
	public void removeColumn(int index) {
		for (Row row : rows) {
			row.removeColumn(index);
		}
		--width;
	}

	public void addRow() {
		rows.add(new Row(this, width));
	}

	public void removeRow(int index) {
		rows.remove(index);
	}
	
}
