<p>${article.title} - <g:formatDate date="${article.lastUpdated}" type="datetime" style="MEDIUM"/></p>
<p>Contributors: <g:each var="user" in="${article.users}">${user.firstName} </g:each></p>
<hr>