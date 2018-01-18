package article

import grails.transaction.Transactional

@Transactional
class TagService {
    def processTags(def tags) {
        def tagList = []
        tags.split(' ').each {
            tagList << new Tag(name: it)
        }
        tagList
    }
}
