<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>New Article</title>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <g:form name="newArticle" url="[controller:'article',action:'newArticle']">
        <div class="nav" role="navigation">
                <g:actionSubmit value="back" action="showAll" class="btn"/>
                <g:actionSubmit value="create" class="btn btn-success"/>
        </div>
            <div class="col-xs-6">
                <div>
                    <label>Title: </label>
                    <g:textField name="title" class="form-control"/><br/>
                </div>
                <div>
                    <label>Tags: </label>
                    <g:textField name="tags" class="form-control"/><br/>
                </div>
                <div>
                    <g:textArea name="content" class="form-control" rows="7" cols="70"/>
                </div>
            </div>
            <div class="col-xs-6">
            </div>
        </g:form>
    </body>
</html>