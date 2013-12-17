<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Dojo Excell</title>
	<link rel="stylesheet" type="text/css" href="css/dojo-excel.css">
	<script type="text/javascript" src="js/vendor/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/dojo-excel.js"></script>
</head>
<body>
	<div class="toolbar">
		<ul>
			<li class="undo-icon"><a href="javascript:undo()"><span>undo</span></a></li>
			<li class="redo-icon"><a href="javascript:redo()"><span>redo</span></a></li>
		</ul>
	</div>
	<form id="actionForm" action="excel" method="post">
		<input type="hidden" name="operation" />
		<input type="hidden" name="position" />
		<input type="hidden" name="value" />
	</form>
	<table class="excel-table">
		<tr><td></td>
			<c:forEach items="${table.columns}" var="column">
			<td class="remove-icon"><a href="javascript:removeColumn(${column})"><span>x</span></a></td>
			</c:forEach>
			<td class="add-icon"><a href="javascript:addColumn()"><span>+</span></a></td>
		</tr>
		<c:forEach items="${table.rows}" var="row">
		<tr><td class="remove-icon"><a href="javascript:removeRow(${row.index})"><span>x</span></a></td>
			<c:forEach items="${row.cells}" var="cell">
				<td class="excel-cell" data-position="${cell.position}"><span>${cell.value}</span></td>
			</c:forEach>
		</tr>
		</c:forEach>
		<tr><td class="add-icon"><a href="javascript:addRow()"><span>+</span></a></td></tr>
	</table>
</body>
</html>
