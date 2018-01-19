package article

import grails.plugins.springsecurity.Secured

class ArticleController {
    def articleService
    def springSecurityService

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def edit(Long id) {
        def tags
        def article = articleService.getById(id)
        if (article != null) {
            tags = article?.tags*.name.join(' ')
        }
        render(view: "edit", model: [article: article, tags: tags])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def createOrUpdate(Long id) {
        def article = id != null ? articleService.getById(id) : new Article()
        bindData(article, params, [exclude: ['tags']])
        if (!article.validate()) {
            redirect(action: 'edit')
        } else {
            def tags = params.tags
            articleService.createOrUpdate(article, tags)
            redirect(action: 'showAll')
        }

    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def showAll = {
        def user = springSecurityService.currentUser
        def articles = articleService.getAllArticles()
        Collections.reverse(articles.sort { it.dateCreated })
        render(view: "showAll", model: [user: user, articles: articles])
    }

    @Secured(["ROLE_ADMIN"])
    def delete(Long id) {
        articleService.delete(id)
        redirect(action: 'showAll')
    }
}
