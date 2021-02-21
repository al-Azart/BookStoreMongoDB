/**
 * 
 */


$(function () {
	var localTable =$('#main_table').dataTable({
    	
		"ordering": false,
		"bProcessing": true,
        "iDisplayLength": 10,
        "bPaginate": true,
        "sPaginationType": "full_numbers"
	});
});

$(document).ready(function() {
    var table = $('#main_table').DataTable();
 
    $('#main_table tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
 
    $('#button').click( function () {
        table.row('.selected').remove().draw( false );
    } );
} );

function buyBook(book_id, book_title){
	document.getElementById("book_id").value=book_id;
	document.getElementById("book_title").value=book_title;
	document.getElementById("action").value="buy_book";
}