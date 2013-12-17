
function addColumn() {
	$("#actionForm input[name=operation]").val("addColumn");
	$("#actionForm").submit();
}

function removeColumn(index) {
	$("#actionForm input[name=operation]").val("removeColumn");
	$("#actionForm input[name=value]").val(index);
	$("#actionForm").submit();
}

function addRow() {
	$("#actionForm input[name=operation]").val("addRow");
	$("#actionForm").submit();
}

function removeRow(index) {
	$("#actionForm input[name=operation]").val("removeRow");
	$("#actionForm input[name=value]").val(index);
	$("#actionForm").submit();
}

function modify(position, newValue) {
	$("#actionForm input[name=operation]").val("modify");
	$("#actionForm input[name=position]").val(position);
	$("#actionForm input[name=value]").val(newValue);
	$("#actionForm").submit();
}

function undo() {
	$("#actionForm input[name=operation]").val("undo");
	$("#actionForm").submit();
}

function redo() {
	$("#actionForm input[name=operation]").val("redo");
	$("#actionForm").submit();
}

$(document).ready(function() {
    $('td.excel-cell').click(function () {
        var span = $(this).find('span');
        var editor = $( "<input class='cell-editor' type='text'/>" );
        editor.val(span.text());
        span.hide();
        $(this).append(editor);
        editor.focus();
        editor.focusout(function() {
            var cell = $(this).parent();
            var newValue = $(this).val();
            $(this).remove();
            cell.find('span').text(newValue);
            cell.find('span').show();
            modify(cell.attr('data-position'), newValue);
        });
    });
});