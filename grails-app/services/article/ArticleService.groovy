package article

import grails.transaction.Transactional

@Transactional
class ArticleService {
    def springSecurityService
    def tagService

    def getAllArticles() {
        Article.list()
    }

    def create(Article newArticle, String tags) {
        def tagList = tagService.processTags(tags)
        newArticle.tags = tagList
        def user = User.get(springSecurityService.currentUser.id)
        user.contributions << newArticle
    }
}
