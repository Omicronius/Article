<g:link action="read" id="${article.id}">${article.title}</g:link> - <g:formatDate date="${article.lastUpdated}" type="datetime" style="MEDIUM"/><br>
Contributors: <g:each var="user" in="${article.users}">${user.firstName} </g:each>
<hr>