package article

import grails.plugins.springsecurity.Secured
import grails.validation.ValidationException

class ArticleController {
    def articleService
    def springSecurityService

    final PAGINATION_MAX_DEFAULT = 4
    final PAGINATION_MAX_LIMIT = 50

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def edit = {
        def article
        if (params.id && params.id.isNumber()) {
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
        if (params.id && params.id.isNumber()) {
            article = articleService.getById(params.id as Long)
        }
        if (!article) {
            article = new Article()
        }

        bindData(article, params, [exclude: ['tags']])
        try {
            articleService.createOrUpdate(article, params.tags)
            redirect(action: 'showAll')
        } catch (ValidationException e) {
            render(view: "edit", model: [article: article, tags: params.tags])
        }
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def showAll = {
        processPaginationParams()
        def searchResult = articleService.getPagedArticles(params.offset, params.max)
        def user = springSecurityService.currentUser
        def infoChart = articleService.getInfoChart()
        render(view: "search", model: [
                user     : user,
                articles : searchResult.articles,
                total    : searchResult.count,
                infoChart: infoChart])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def search = {
        processPaginationParams()
        def searchResult = articleService.findBySearchWord(params.search, params.max, params.offset)
        def user = springSecurityService.currentUser
        def infoChart = articleService.getInfoChart()
        render(view: "search", model: [
                user     : user,
                articles : searchResult.articles,
                total    : searchResult.count,
                infoChart: infoChart])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def tag = {
        def tag = Tag.findByName(params.id)
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
        if (params.id && params.id.isNumber()) {
            article = articleService.getById(params.id as Long)
        }
        def user = springSecurityService.currentUser
        def infoChart = articleService.getInfoChart()
        render(view: "read", model: [user: user, article: article, infoChart: infoChart])
    }

    @Secured(["ROLE_ADMIN"])
    def delete = {
        if (params.id && params.id.isLong()) {
            articleService.delete(params.id)
        }
        redirect(action: 'showAll')
    }

    def index = {
        redirect(action: "showAll")
    }

    private processPaginationParams() {
        params.offset = (params.offset && params.offset.isNumber()) ? params.int('offset') : 0
        params.max = (params.max && params.max.isNumber()) ? Math.min(params.int('max'), PAGINATION_MAX_LIMIT) : PAGINATION_MAX_DEFAULT
    }
}
