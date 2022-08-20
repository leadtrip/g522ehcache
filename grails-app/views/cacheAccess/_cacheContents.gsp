<g:javascript>
    $(document).ready(function() {
        $('#cacheTable').DataTable({
            "pageLength": 10
        });
    })
</g:javascript>

<table id="cacheTable" class="table table-bordered">
    <thead>
        <tr>
            <th>Key</th>
            <th>Value</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${cacheContents}" var="cacheItem">
            <tr>
                <td>${cacheItem.key}</td>
                <td>${cacheItem.value}</td>
            </tr>
        </g:each>
    </tbody>
</table>