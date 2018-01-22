<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>New Article</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <g:form controller="article" action="createOrUpdate" id="${article?.id}">
        <div class="nav" role="navigation">
            <li><g:link class="home" action="showAll">Home</g:link></li>
            <li><g:link class="create" action="edit">New article</g:link></li>
        </div>
            <g:renderErrors bean="${article}" />
        <div class="col-xs-6">
            <div class="value ${hasErrors(bean:article, field:'title','errors')}">
                Title:
                <g:textField name="title" class="form-control" value="${fieldValue(bean:article, field:'title')}"/><br/>
            </div>
            <div class="value ${hasErrors(bean:article, field:'tags','errors')}">
                Tags:
                ${flash.message}
                <g:textField name="tags" class="form-control" value="${tags}"/><br/>
            </div>
            <div class="value ${hasErrors(bean:article, field:'content','errors')}">
                Content:
                <g:textArea name="content" class="form-control" rows="7" cols="70" value="${fieldValue(bean:article, field:'content')}"/>
            </div>
        </div>
        <div class="col-xs-6">
            <g:submitButton name="save" class="btn btn-success" style="margin: 25px;"/>
        </div>
        </g:form>
    </body>
</html>