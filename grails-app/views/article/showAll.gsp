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
        <div class="col-md-10 col-md-offset-1">
            <g:render template="articles" collection="${articles}" var="article"/>
            <g:paginate total="${total}" action="showAll" max="${params.max}" offset="${params.offset}"/>
        </div>
    </body>
</html>