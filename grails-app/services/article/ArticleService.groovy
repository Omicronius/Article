package article

import org.springframework.transaction.annotation.Transactional

@Transactional
class ArticleService {
    def springSecurityService
    def userService
    def tagService

    def getById(Long id) {
        Article.get(id)
    }

    def getAllArticles() {
        Article.list()
    }

    def createOrUpdate(Article article, String tags) {
        def tagList = tagService.processTags(tags)
        article.tags = tagList
        springSecurityService.currentUser.articles << article
    }

    def delete(Long id) {
        def article = Article.get(id)
        def users = userService.getUsersByArticle(article)
        users.each { it.articles.remove(article) }
        article.delete()
    }
}
