<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>All articles</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
    <g:render template="nav"/>
    <div class="col-md-8 col-md-offset-1">
        <g:if test="${params.search}">
            <h4 style="text-align: center">Searching result for: ${params.search}</h4>
        </g:if>
        <g:if test="${articles.isEmpty()}">
            <h4>No articles has been found.</h4>
        </g:if>
    </div>
    <div class="col-md-10 col-md-offset-1">
        <g:render template="articles" collection="${articles}" var="article"/>
        <g:paginate total="${total}" action="search" max="${params.max}" offset="${params.offset}" params="${params}"/>
    </div>
    <g:javascript>highlightWords("${params.search}", "*")</g:javascript>
    </body>
</html>