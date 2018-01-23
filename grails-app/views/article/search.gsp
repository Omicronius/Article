<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>All articles</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <g:render template="nav"/>
        <div class="col-md-8">
            <g:if test="${articles.isEmpty()}">
                <h3>No articles has been found.</h3>
            </g:if>
        </div>
        <div class="col-md-8">
            <g:render template="articles" collection="${articles}" var="article"/>
            <g:paginate total="${total}" action="search" max="${params.max}" id="${params.search}" offset="${params.offset}"/>
        </div>
        <div class="col-md-3" style="margin: 10px">
            <g:render template="profile" bean="${user}"/>
        </div>
        <div class="col-md-4" style="float:right">
            <h4 class="well"> Last updated:</h4>
            <g:render template="recent" collection="${infoChart.recent}" var="article"/>
            <h4 class="well"> Top articles:</h4>
            <g:render template="top" collection="${infoChart.top}" var="article"/>
        </div>
    </body>
</html>