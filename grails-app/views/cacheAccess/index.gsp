<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cache</title>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.12.1/datatables.min.css"/>

    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.12.1/datatables.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $( ".addToCache" ).click( function( event ) {
                $.ajax( {
                    url: "${g.createLink( controller:'cacheAccess', action:'addToCache')}",
                    type: "post",
                    timeout: 10000,
                    success: function(data) {
                        $( "#resultDiv" ).addClass( 'alert alert-info' ).text( 'Added to cache' )
                        $( '#cacheContentsDiv').html(data)
                    },
                    error: function(xhr, status, errorThrown) {
                        $( "#resultDiv" ).addClass( 'alert alert-danger' ).append( 'Unable to add to cache ' + xhr.responseText )
                    },
                    beforeSend: function() { showSpinner( true ) },
                    complete: function() { showSpinner( false ) }
                });
            })

            $( ".getLastAddedFromCache" ).click( function( event ) {
                $.ajax( {
                    url: "${g.createLink( controller:'cacheAccess', action:'getLastAddedFromCache')}",
                    type: "post",
                    timeout: 10000,
                    success:( function ( data, status, jqXHR ) {
                        $( '#lastAdded' ).val( jqXHR.responseText );
                        $( '#getLastAddedFromCacheDiv' ).modal();
                    }),
                    error: function(xhr, status, errorThrown) {
                        $( "#resultDiv" ).addClass( 'alert alert-danger' ).append( 'Unable to get last added to cache ' + xhr.responseText )
                    },
                    beforeSend: function() { showSpinner( true ) },
                    complete: function() { showSpinner( false ) }
                });
            })
        });
    </script>
</head>

<body>
    <div id="resultDiv"></div>

    <br>

    <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-success addToCache" name="addToCache">Add to cache</button>
        <button type="button" class="btn btn-info getLastAddedFromCache" name="getLastAddedFromCache">Get last added to cache</button>
        <button type="button" class="btn btn-danger clearCache" name="clearCache">Clear cache</button>
    </div>

    <br>

    <div id="cacheContentsDiv"></div>

<div class="modal fade" id="getLastAddedFromCacheDiv" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="getLastAddedFromCacheHeader" class="modal-title">Last added to cache</h4>
            </div>
            <div class="modal-body">
                <g:form class="form-horizontal" name="getLastAddedFromCacheForm">

                    <div class="form-group" class="form-horizontal">
                        <label class="control-label col-md-1" for="lastAdded"></label>
                        <div class='col-md-12'>
                            <g:textField disabled="disabled" class="form-control" name="lastAdded"></g:textField>
                        </div>
                    </div>

                </g:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

</body>

</html>