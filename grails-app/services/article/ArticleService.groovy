package article

import grails.gorm.DetachedCriteria
import grails.validation.ValidationException

class ArticleService {
    def springSecurityService
    def userService
    def tagService

    final int TOP_VIEWS_ARTICLES_AMOUNT = 3
    final int RECENT_ARTICLES_AMOUNT = 3

    def getById(Long id) {
        Article.get(id)
    }

    def getArticlesByTag(Tag tag) {
        Article.executeQuery('select a from Article a where :tag in elements(a.tags)', [tag: tag])
    }

    def getPagedArticles(offset, max) {
        def articles = Article.list(sort: "lastUpdated", order: "desc", max: max, offset: offset)
        [articles: articles, count: Article.count()]
    }

    def findBySearchWord(String searchWord, Integer max, Integer offset) {
        def criteria = new DetachedCriteria(Article).build {
            like("title", "%${searchWord}%")
        }
        def articles = criteria.list(sort: "lastUpdated", order: "desc", max: max, offset: offset)
        [articles: articles, count: criteria.count()]
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
        def topArticles = Article.list(sort: "views", order: 'desc', max: TOP_VIEWS_ARTICLES_AMOUNT)
        def recentArticles = Article.list(sort: "lastUpdated", order: 'desc', max: RECENT_ARTICLES_AMOUNT)
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
