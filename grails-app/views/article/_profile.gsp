<sec:ifLoggedIn>
    Hello, ${user.firstName}!
    <g:link controller="user" action="logout" style="float: right">
        <button type="button" class="btn btn-default btn-sm">Logout</button>
    </g:link>
</sec:ifLoggedIn>