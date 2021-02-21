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

$(function () {
	var addlTable =$('#add_book').dataTable({
    	"searching": false,
		"bPaginate": false,
		"shoving": false,
		"bProcessing": false,
        "iDisplayLength": 10,
        "bPaginate": false,
		"bLengthChange": false,
		"ordering": false,
    	"bFilter": true,
   		"bInfo": false,
    	"bAutoWidth": false ,
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

function deleteBook(book_id){
	document.getElementById("book_id").value=book_id;
	document.getElementById("action").value="delete_book";
}




