$(function() {
    var dialog, form,

        name = $( "#name" ),
        description = $( "#description" ),
        allFields = $( [] ).add( name ).add( description );

    function addRole() {
            $( "#roles tbody" ).append( "<tr>" +
            "<td>" + name.val() + "</td>" +
            "<td>" + description.val() + "</td>" +
            "</tr>" );
            dialog.dialog( "close" );
    }

    dialog = $( "#dialog-form" ).dialog({
        autoOpen: false,
        height: 300,
        width: 350,
        modal: true,
        buttons: {
            "Create an account": addRole,
            Cancel: function() {
                dialog.dialog( "close" );
            }
        },
        close: function() {
            form[ 0 ].reset();
        }
    });

    form = dialog.find( "form" ).on( "submit", function( event ) {
        event.preventDefault();
        addRole();
    });

    $( "#create-user" ).button().on( "click", function() {
        dialog.dialog("open");
    });
});