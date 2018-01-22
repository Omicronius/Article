package article

import grails.plugins.springsecurity.Secured
import grails.validation.ValidationException

class ArticleController {
    def articleService
    def springSecurityService

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def edit = {
        def article
        if (params.id && params.id.isLong()) {
            article = articleService.getById(params.id as Long)
        }
        def tags
        if (article) {
            tags = article?.tags*.name.join(' ')
        }
        render(view: "edit", model: [article: article, tags: tags])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def createOrUpdate = {
        def article = null
        if (params.id && params.id.isLong()) {
            article = articleService.getById(params.id as Long)
        }
        if (!article) {
            article = new Article()
        }

        bindData(article, params, [exclude: ['tags']])
        try {
            articleService.createOrUpdate(article, params.tags as String)
            redirect(action: 'showAll')
        } catch (ValidationException e) {
            render(view: "edit", model: [article: article])
        }
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def showAll = {
        def user = springSecurityService.currentUser
        def articles = articleService.getAllArticles()
        def infoChart = articleService.getInfoChart()
        render(view: "showAll", model: [user: user, articles: articles, infoChart: infoChart])
    }

    @Secured(["ROLE_ADMIN"])
    def delete = {
        if (params.id && params.id.isLong()) {
            articleService.delete(params.id as Long)
        }
        redirect(action: 'showAll')
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def tag = {
        def tag = Tag.findByName(params.id as String)
        def articles = []
        if (tag) {
            articles = articleService.getArticlesByTag(tag)
        }
        def user = springSecurityService.currentUser
        def infoChart = articleService.getInfoChart()
        render(view: "showAll", model: [user: user, articles: articles, infoChart: infoChart])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def read = {
        def article
        if (params.id && params.id.isLong()) {
            article = articleService.getById(params.id as Long)
        }
        def user = springSecurityService.currentUser
        def infoChart = articleService.getInfoChart()
        render(view: "read", model: [user: user, article: article, infoChart: infoChart])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def search = {
        def articles = articleService.findBySearchWord(params.search as String)
        def user = springSecurityService.currentUser
        def infoChart = articleService.getInfoChart()
        render(view: "showAll", model: [user: user, articles: articles, infoChart: infoChart])
    }
}
