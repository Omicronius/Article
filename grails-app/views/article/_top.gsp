<g:link action="read" id="${article.id}">${article.title}</g:link> - ${article.views} views
Contributors: <g:each var="user" in="${article.users}">${user.firstName} </g:each>
<hr>
