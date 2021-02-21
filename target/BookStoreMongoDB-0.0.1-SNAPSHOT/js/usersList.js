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

function deleteUser(user_id){
	document.getElementById("user_id").value=user_id;
	document.getElementById("action_user").value="delete_user";
}