package article

import grails.plugins.springsecurity.Secured

class UserController {

    @Secured(["permitAll"])
    def logout() {
        session.invalidate()
        redirect(uri: "/login/auth")
    }
}
