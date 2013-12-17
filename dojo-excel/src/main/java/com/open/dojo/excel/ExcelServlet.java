package com.open.dojo.excel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.open.dojo.excel.model.Table;

public class ExcelServlet extends HttpServlet {
	
	private static final long serialVersionUID = 8365114873147605244L;

	private static final String DISPLAY_EXCELL_JSP = "/excel.jsp";
	private static final String POSITION_SEPARATOR = "-";
	private static final String POSITION_PARAMETER = "position";
	private static final String VALUE_PARAMETER = "value";
	private static final String MODIFY_OPERATION = "modify";
	private static final String REMOVE_ROW_OPERATION = "removeRow";
	private static final String REMOVE_COLUMN_OPERATION = "removeColumn";
	private static final String ADD_ROW_OPERATION = "addRow";
	private static final String ADD_COLUMN_OPERATION = "addColumn";
	private static final String UNDO_OPERATION = "undo";
	private static final String REDO_OPERATION = "redo";
	private static final String TABLE_ATTRIBUTE = "table";
	private static final String OPERATION_PARAMETER = "operation";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Table table = getTable(req.getSession());
		displayTable(table, req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Table table = getTable(req.getSession());
		String operation = req.getParameter(OPERATION_PARAMETER);
		if (operation.equals(ADD_COLUMN_OPERATION)) {
			table.addCoumn();
		} else if (operation.equals(ADD_ROW_OPERATION)) {
			table.addRow();
		} else if (operation.equals(REMOVE_COLUMN_OPERATION)) {
			int index = Integer.parseInt(req.getParameter(VALUE_PARAMETER));
			table.removeColumn(index);
		} else if (operation.equals(REMOVE_ROW_OPERATION)) {
			int index = Integer.parseInt(req.getParameter(VALUE_PARAMETER));
			table.removeRow(index);
		} else if (operation.equals(MODIFY_OPERATION)) {
			String[] elements = req.getParameter(POSITION_PARAMETER).split(POSITION_SEPARATOR);
			int line = Integer.parseInt(elements[0]);
			int column = Integer.parseInt(elements[1]);
			String value = req.getParameter(VALUE_PARAMETER);
			table.getRows().get(line).getCells().get(column).setValue(value);
		} else if (operation.equals(UNDO_OPERATION)) {
		} else if (operation.equals(REDO_OPERATION)) {
		}
		displayTable(table, req, resp);
	}
	
	private void displayTable(Table table, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute(TABLE_ATTRIBUTE, table);
		getServletContext().getRequestDispatcher(DISPLAY_EXCELL_JSP).forward(req, resp);
	}
	
	private Table getTable(HttpSession session) {
		Table table = (Table) session.getAttribute(TABLE_ATTRIBUTE);
		if (table == null) {
			table = new Table();
			session.setAttribute(TABLE_ATTRIBUTE, table);
		}
		return table;
	}
	
}
