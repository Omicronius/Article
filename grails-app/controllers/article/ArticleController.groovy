package article

import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.StringUtils

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
        if (!article.validate() || StringUtils.isBlank(params.tags as String)) {
            if (StringUtils.isBlank(params.tags as String)) {
                flash.message = "Please enter tags."
            }
            render(view: "edit", model: [article: article])
        } else {
            def tags = params.tags
            articleService.createOrUpdate(article, tags)
            redirect(action: 'showAll')
        }

    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def showAll() {
        def user = springSecurityService.currentUser
        def articles = articleService.getAllArticles()
        def topArticles = articleService.getTopArticles()
        render(view: "showAll", model: [user: user, articles: articles, topArticles: topArticles])
    }

    @Secured(["ROLE_ADMIN"])
    def delete(Long id) {
        articleService.delete(id)
        redirect(action: 'showAll')
    }

    @Secured(["ROLE_USER", "ROLE_ADMIN"])
    def tag = {
        String tagName = params.id
        def tag = Tag.findByName(tagName)
        def articles = articleService.getArticlesByTag(tag)
        def topArticles = articleService.getTopArticles()
        def user = springSecurityService.currentUser
        render(view: "showAll", model: [user: user, articles: articles, topArticles: topArticles])
    }
}
