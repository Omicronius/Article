package article

import grails.validation.ValidationException

class ArticleService {
    def springSecurityService
    def userService
    def tagService

    final LAST_UPDATED_SORTING = [sort: "lastUpdated", order: 'desc']
    final TOP_VIEWS_SORTING = [sort: "views", order: 'desc']
    final int TOP_VIEWS_ARTICLES_AMOUNT = 5
    final int RECENT_ARTICLES_AMOUNT = 3

    def getById(Long id) {
        Article.get(id)
    }

    def getArticlesByTag(Tag tag) {
        Article.executeQuery('select a from Article a where :tag in elements(a.tags)', [tag: tag])
    }

    def getAllArticles() {
        Article.list(LAST_UPDATED_SORTING)
    }

    def createOrUpdate(Article article, String tags) {
        def tagList = tagService.processTags(tags)
        article.tags = tagList
        if (article.validate()) {
            springSecurityService.currentUser.articles << article
        } else {
            throw new ValidationException("Tags have not been specified.", article.errors)
        }
    }

    def getInfoChart() {
        def topArticles = Article.list(TOP_VIEWS_SORTING).take(TOP_VIEWS_ARTICLES_AMOUNT)
        def recentArticles = getAllArticles().take(RECENT_ARTICLES_AMOUNT)
        [top: topArticles, recent: recentArticles]
    }

    def delete(Long id) {
        def article = Article.get(id)
        if (article) {
            def users = userService.getUsersByArticle(article)
            users.each { it.articles.remove(article) }
            article.delete()
        }
    }
}
