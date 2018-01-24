<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>All articles</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <g:render template="nav"/>
        <div class="col-md-4">
            <div style="margin: 10px">
                <g:render template="profile" bean="${user}"/>
            </div>
            <g:link class="btn btn-default fix-wid center" action="showAll">All articles</g:link>
            <g:link controller="user" action="logout" class="btn btn-default fix-wid center">Logout</g:link>
        </div>
        <div class="col-md-4">
            <h4 class="well"> Last updated:</h4>
            <g:render template="recent" collection="${infoChart.recent}" var="article"/>
        </div>
        <div class="col-md-4">
            <h4 class="well"> Top articles:</h4>
            <g:render template="top" collection="${infoChart.top}" var="article"/>
        </div>
    </body>
</html>