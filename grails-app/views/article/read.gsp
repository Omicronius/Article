<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Article - ${article?.title}</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <g:render template="nav"/>
        <div class="col-md-8">
            <g:if test="${!article}">
                <h3>No articles has been found.</h3>
            </g:if>
        </div>
        <div class="col-md-10 col-md-offset-1">
            <g:if test="${article}">
                <g:render template="current" bean="${article}"/>
            </g:if>
        </div>
    </body>
</html>