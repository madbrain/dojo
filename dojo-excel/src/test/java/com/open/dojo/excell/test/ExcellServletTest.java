package com.open.dojo.excell.test;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.open.dojo.excel.ExcelServlet;
import com.open.dojo.excel.model.Table;

public class ExcellServletTest {

	@Test
	public void testCreation() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("GET");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		Mockito.verify(req).setAttribute(Mockito.eq("table"), Mockito.any(Table.class));
	}
	
	@Test
	public void testTableInSession() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Table table = new Table();
		
		Mockito.when(req.getMethod()).thenReturn("GET");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(session.getAttribute("table")).thenReturn(table);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(table, tableCaptor.getValue());
	}
	
	@Test
	public void testAddColumn() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("addColumn");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(2, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(3, getColumnCount(tableCaptor.getValue()));
	}
	
	@Test
	public void testAddColumnAndUndo() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("addColumn");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		Mockito.when(session.getAttribute("table")).thenReturn(new Table());
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);

		HttpServletRequest req2 = Mockito.mock(HttpServletRequest.class);
		Mockito.when(req2.getMethod()).thenReturn("POST");
		Mockito.when(req2.getParameter("operation")).thenReturn("undo");
		Mockito.when(req2.getSession()).thenReturn(session);
		servlet.service(req2, resp);
				
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(2, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(2, getColumnCount(tableCaptor.getValue()));
	}
	
	@Test
	public void testAddRow() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("addRow");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(3, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(2, getColumnCount(tableCaptor.getValue()));
	}
	
	@Test
	public void testRemoveColumn() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("removeColumn");
		Mockito.when(req.getParameter("value")).thenReturn("0");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(2, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(1, getColumnCount(tableCaptor.getValue()));
	}
	
	@Test
	public void testRemoveRow() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("removeRow");
		Mockito.when(req.getParameter("value")).thenReturn("0");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(1, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(2, getColumnCount(tableCaptor.getValue()));
	}
	
	@Test
	public void testModify() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("modify");
		Mockito.when(req.getParameter("position")).thenReturn("0-1");
		Mockito.when(req.getParameter("value")).thenReturn("XXX");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(2, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(2, getColumnCount(tableCaptor.getValue()));
		Assert.assertEquals("XXX", tableCaptor.getValue().getRows().get(0).getCells().get(1).getValue());
	}
	
	@Test
	public void testBadRequest() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("foo");
		Mockito.when(req.getParameter("position")).thenReturn("0-1");
		Mockito.when(req.getParameter("value")).thenReturn("XXX");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(2, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(2, getColumnCount(tableCaptor.getValue()));
		Assert.assertEquals("", tableCaptor.getValue().getRows().get(0).getCells().get(1).getValue());
	}
	
	@Test
	public void testAddRowAndUndo() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("addRow");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		Mockito.when(session.getAttribute("table")).thenReturn(new Table());
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		HttpServletRequest req2 = Mockito.mock(HttpServletRequest.class);
		Mockito.when(req2.getMethod()).thenReturn("POST");
		Mockito.when(req2.getParameter("operation")).thenReturn("undo");
		Mockito.when(req2.getSession()).thenReturn(session);
		servlet.service(req2, resp);
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req2).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(2, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(2, getColumnCount(tableCaptor.getValue()));
	}
	
	@Test
	public void testAddRowAndUndoAndRedo() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("addRow");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		Mockito.when(session.getAttribute("table")).thenReturn(new Table());
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		HttpServletRequest req2 = Mockito.mock(HttpServletRequest.class);
		Mockito.when(req2.getMethod()).thenReturn("POST");
		Mockito.when(req2.getParameter("operation")).thenReturn("undo");
		Mockito.when(req2.getSession()).thenReturn(session);
		servlet.service(req2, resp);
		
		HttpServletRequest req3 = Mockito.mock(HttpServletRequest.class);
		Mockito.when(req3.getMethod()).thenReturn("POST");
		Mockito.when(req3.getParameter("operation")).thenReturn("redo");
		Mockito.when(req3.getSession()).thenReturn(session);
		servlet.service(req3, resp);
		
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req3).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(3, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(2, getColumnCount(tableCaptor.getValue()));
	}
	
	@Test
	public void testCannotRedoAfterExecute() throws ServletException, IOException {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		ServletConfig config = Mockito.mock(ServletConfig.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
		
		Mockito.when(req.getMethod()).thenReturn("POST");
		Mockito.when(req.getParameter("operation")).thenReturn("addRow");
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(config.getServletContext()).thenReturn(context);
		Mockito.when(context.getRequestDispatcher("/excell.jsp")).thenReturn(dispatcher);
		Mockito.when(session.getAttribute("table")).thenReturn(new Table());
		
		ExcelServlet servlet = new ExcelServlet();
		servlet.init(config);
		
		servlet.service(req, resp);
		
		HttpServletRequest req2 = Mockito.mock(HttpServletRequest.class);
		Mockito.when(req2.getMethod()).thenReturn("POST");
		Mockito.when(req2.getParameter("operation")).thenReturn("undo");
		Mockito.when(req2.getSession()).thenReturn(session);
		servlet.service(req2, resp);
		
		HttpServletRequest req3 = Mockito.mock(HttpServletRequest.class);
		Mockito.when(req3.getMethod()).thenReturn("POST");
		Mockito.when(req3.getParameter("operation")).thenReturn("addRow");
		Mockito.when(req3.getSession()).thenReturn(session);
		servlet.service(req3, resp);
		
		HttpServletRequest req4 = Mockito.mock(HttpServletRequest.class);
		Mockito.when(req4.getMethod()).thenReturn("POST");
		Mockito.when(req4.getParameter("operation")).thenReturn("redo");
		Mockito.when(req4.getSession()).thenReturn(session);
		servlet.service(req4, resp);
		
		
		ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
		Mockito.verify(req4).setAttribute(Mockito.eq("table"), tableCaptor.capture());
		Assert.assertEquals(3, tableCaptor.getValue().getRows().size());
		Assert.assertEquals(2, getColumnCount(tableCaptor.getValue()));
	}
	
	
	private int getColumnCount(Table table) {
		int count = 0;
		Iterator<Integer> it = table.getColumns();
		while (it.hasNext()) {
			it.next();
			++count;
		}
		return count;
	}
}
