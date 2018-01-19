package article

import grails.plugins.springsecurity.Secured

class ArticleController {
    def articleService
    def springSecurityService

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def edit(Long id) {
        def article
        def tags
        if (id != null) {
            article = articleService.getById(id)
            tags = article?.tags*.name.join(' ')
        }
        render(view: "edit", model: [article: article, tags: tags])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def createOrUpdate() {
        def article = params.id != null ? articleService.getById(params.id as Long) : new Article()
        bindData(article, params, [exclude: ['tags']])
        def tags = params.tags
        articleService.createOrUpdate(article, tags)
        redirect(action: 'showAll')
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def showAll = {
        def user = springSecurityService.currentUser
        def articles = articleService.getAllArticles()
        render(view: "showAll", model: [user: user, articles: articles])
    }

    @Secured(["ROLE_ADMIN"])
    def delete(Long id) {
        articleService.delete(id)
        redirect(action: 'showAll')
    }
}
