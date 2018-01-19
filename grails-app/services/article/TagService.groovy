package article

import grails.transaction.Transactional

@Transactional
class TagService {
    def processTags(String tags) {
        def tagList = []
        tags.split(' ').each {
            def tag = Tag.findByName(it) ?: new Tag(name: it)
            tagList.add(tag)
        }
        tagList
    }
}
