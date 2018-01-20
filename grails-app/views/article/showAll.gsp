<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>All articles</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul><li><g:link class="create" action="edit">New article</g:link></li></ul>
        </div>
        <div class="col-md-8">
            <g:render template="articles" collection="${articles}" var="article"/>
        </div>
        <div class="col-md-3" style="margin: 10px">
            <g:render template="profile" bean="${user}"/>
        </div>
        <div class="col-md-4">
            <h3 class="well"> Top articles:</h3>
            <g:render template="top" collection="${topArticles}" var="article"/>
        </div>
    </body>
</html>