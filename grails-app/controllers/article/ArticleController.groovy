package article

import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.StringUtils

class ArticleController {
    def articleService
    def springSecurityService

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def edit = {
        def tags
        def id = params.id as Long
        def article = articleService.getById(id)
        if (article) {
            tags = article?.tags*.name.join(' ')
        }
        render(view: "edit", model: [article: article, tags: tags])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def createOrUpdate = {
        def id = params.id as Long
        def article = articleService.getById(id)
        if (!article) {
            article = new Article()
        }
        bindData(article, params, [exclude: ['tags']])
        if (article.validate() && StringUtils.isNotBlank(params.tags)) {
            def tags = params.tags
            articleService.createOrUpdate(article, tags)
            redirect(action: 'showAll')
        }
        if (StringUtils.isBlank(params.tags as String)) {
            flash.message = "Please enter tags."
        }
        render(view: "edit", model: [article: article])
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def showAll = {
        def user = springSecurityService.currentUser
        def articles = articleService.getAllArticles()
        def topArticles = articleService.getTopArticles()
        render(view: "showAll", model: [user: user, articles: articles, topArticles: topArticles])
    }

    @Secured(["ROLE_ADMIN"])
    def delete = {
        def id = params.id
        if (id && id.isLong()) {
            articleService.delete(id as Long)
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
        def topArticles = articleService.getTopArticles()
        def user = springSecurityService.currentUser
        render(view: "showAll", model: [user: user, articles: articles, topArticles: topArticles])
    }
}
