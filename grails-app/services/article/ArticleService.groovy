package article

import org.springframework.transaction.annotation.Transactional

@Transactional
class ArticleService {
    def springSecurityService
    def userService
    def tagService

    def getAllArticles() {
        Article.list()
    }

    def create(Article newArticle, String tags) {
        def tagList = tagService.processTags(tags)
        newArticle.tags = tagList
        def user = User.get(springSecurityService.currentUser.id)
        user.articles << newArticle
    }

    def delete(Long id) {
        def article = Article.get(id)
        def users = userService.getUsersByArticle(article)
        users.each { it.articles.remove(article) }
        article.delete()
    }
}
