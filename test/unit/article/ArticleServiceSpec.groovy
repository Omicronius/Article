package article

import grails.plugins.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ArticleService)
@Mock([Article, Tag, TagService, SpringSecurityService])
class ArticleServiceSpec extends Specification {

    def 'getById'() {
        def article = new Article(
                title: 'some article',
                content: 'some content',
                views: 777,
                dateCreated: new Date(),
                lastUpdated: new Date(),
                tags: [new Tag(name: 'science'), new Tag(name: 'history')]).save()

        when:
        def result = service.getById(article.id)

        then:
        result.id == article.id
        result.title == article.title
        result.content == article.content
        result.views == article.views
        result.dateCreated == article.dateCreated
        result.lastUpdated == article.lastUpdated
        result.tags == article.tags
    }

    def 'create'() {
        def article = new Article(
                title: 'some article',
                content: 'some content',
                views: 777,
                dateCreated: new Date(),
                lastUpdated: new Date()
        )
        def tags = 'tag1 tag2'
//        service.tagService = [processTags: {String t-> }]
//        service.tagService.metaClass.processTags = {String t-> [jalsdj, askas]}

        when:
        service.createOrUpdate(article, tags)

        then:
        Article.list().size() == 1
    }
}
