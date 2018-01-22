package article

import grails.test.spock.IntegrationSpec

class ArticleServiceTest extends IntegrationSpec {
    def articleService
    def springSecurityService

    def 'create new articles with tags'() {
        given:
        def tag1 = 'first'
        def tag2 = 'second'
        def article = new Article(
                title: 'Scientific article',
                content: 'Increased fluctuations in the path of the North Atlantic jet stream',
                views: 1556,
                dateCreated: new Date(),
                lastUpdated: new Date())
        def user = new User(
                username: 'username',
                password: 'password',
                firstName: 'firstName',
                lastName: 'lastName',
                email: 'user@user.com',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                articles: []
        ).save()
        springSecurityService.reauthenticate(user.username, user.password)

        when:
        def articles = Article.list()

        then:
        !articles

        when:
        articleService.createOrUpdate(article, "${tag1} ${tag2}")
        def createdArticle = Article.first()

        then:
        Article.list().size() == 1
        Tag.list().size() == 2
        User.first().articles.contains(article)
        createdArticle.title == article.title
        createdArticle.content == article.content
        createdArticle.views == article.views
        createdArticle.dateCreated == article.dateCreated
        createdArticle.lastUpdated == article.lastUpdated
        article.tags.size() == 2
        createdArticle.tags*.name.contains(tag1)
        createdArticle.tags*.name.contains(tag2)

        cleanup:
        user.delete()
        article.delete()
        Tag.list().each { it.delete() }
    }
}
