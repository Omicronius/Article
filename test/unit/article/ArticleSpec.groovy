package article

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.domain.DomainClassUnitTestMixin

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Article)
@Mock(Tag)
class ArticleSpec extends DomainClassUnitTestMixin {

    void 'create'() {
        new Article(
                title: 'some article',
                content: 'some content',
                views: 777,
                dateCreated: new Date(),
                lastUpdated: new Date(),
                tags: [new Tag(name: 'science'), new Tag(name: 'history')]).save(flush: true)

        assertEquals(Article.list().size(), 1)
    }

}
