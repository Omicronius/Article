<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>New Article</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <g:form controller="article" action="createOrUpdate" id="${article?.id}">
        <div class="nav" role="navigation">
                <g:actionSubmit value="back" action="showAll" class="btn"/>
        </div>
        <div class="col-xs-6">
            <div>
                <label>Title:</label>
                <g:textField name="title" class="form-control" value="${article?.title}"/><br/>
            </div>
            <div>
                <label>Tags:</label>
                <g:textField name="tags" class="form-control" value="${tags}"/><br/>
            </div>
            <div>
                <g:textArea name="content" class="form-control" rows="7" cols="70" value="${article?.content}"/>
            </div>
        </div>
        <div class="col-xs-6">
            <g:actionSubmit value="save" action="createOrUpdate" class="btn btn-success" style="margin: 25px;"/>
        </div>
        </g:form>
    </body>
</html>