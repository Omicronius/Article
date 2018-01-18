package article

import grails.plugins.springsecurity.Secured

class ArticleController {
    def articleService
    def springSecurityService

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def newArticle = {
        render(view: "newArticle")
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def create = {
        def newArticle = new Article()
        bindData(newArticle, params, [exclude: ['tags']])
        def tags = params.tags
        articleService.create(newArticle, tags)
        redirect (action: 'showAll')
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def showAll = {
        def user = springSecurityService.currentUser
        def articles = articleService.getAllArticles()
        render(view: "showAll", model: [user: user, articles: articles])
    }
}
