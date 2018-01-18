<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>All articles</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul><li><g:link class="create" action="newArticle">New article</g:link></li></ul>
        </div>
        <div class="col-md-8">
            <g:each in="${articles.sort{it.dateCreated}}">
                <p><h3>${it.title}</h3></p>
                <p>Views: ${it.views}</p>
                <p>CreateDate: <g:formatDate date="${it.dateCreated}" type="datetime" style="MEDIUM"/></p>
                <p>Contributors: <g:each var="contributor" in="${it.contributors}">${contributor.firstName} </g:each></p>
                <p>Tags: <g:each var="tag" in="${it.tags}">${tag.name} </g:each></p>
                <p>Content: ${it.content}</p>
                <hr>
            </g:each>
        </div>
        <div class="col-md-3" style="margin: 10px">
            <g:if test="${user}">
                Hello, ${user.firstName}!
                <g:link controller="user" action="logout" style="float: right">
                    <button type="button" class="btn btn-default btn-sm">Logout</button>
                </g:link>
            </g:if>
        </div>
        <div class="col-md-4">
            <h3 class="well"> Top articles:</h3>
            <g:each in="${articles.sort{-it.views}}">
                <p><h4>${it.title} - ${it.views} views</h4></p>
                <p>Contributors: <g:each var="contributor" in="${it.contributors}">${contributor.firstName} </g:each></p>
                <hr>
            </g:each>
        </div>
    </body>
</html>