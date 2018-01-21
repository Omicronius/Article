package article

import org.apache.commons.lang.StringUtils

class TagService {
    def processTags(String tags) {
        def tagList = []
        tags?.split(' ')?.each {
            if (StringUtils.isNotBlank(it) && !tagList*.name.contains(it)) {
                def tag = Tag.findByName(it) ?: new Tag(name: it)
                tagList.add(tag)
            }
        }
        tagList
    }
}
