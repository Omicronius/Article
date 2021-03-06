<h3>
    <g:link action="read" id="${article.id}">${article.title}</g:link>
    <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_USER'>
        <g:link action="edit" class="btn btn-small btn-default" id="${article.id}">Edit</g:link>
    </sec:ifAnyGranted>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <g:link action="delete" class="btn btn-small btn-default" style="float: right" id="${article.id}">Delete</g:link>
    </sec:ifAllGranted>
</h3>
<p>Views: ${article.views}</p>
<p>Last updated:
    <g:formatDate date="${article.lastUpdated}" type="datetime" style="MEDIUM"/>
</p>
<p>Contributors:
    <g:each var="user" in="${article.users}">${user.firstName}</g:each></p>
<p>Tags:
    <g:each var="tag" in="${article.tags}">
        <g:link action="tag" id="${tag.name}">${tag.name}</g:link>
    </g:each></p>
<p>Content: ${article.content}</p>
<hr>