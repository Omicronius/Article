package article

class Article {
    String title
    String content
    Long views = 0
    Date dateCreated
    Date lastUpdated
    static hasMany = [users: User, tags: Tag]
    static belongsTo = User

    static constraints = {
        title size: 1..255, blank: false
        content blank: false
    }

    static mapping = {
        tags validator: {it.size() != 0}
        users joinTable: [name: "user_article", key: 'article_id' ]
        tags joinTable: [name: "article_tag", key: 'article_id' ]
        content type: 'text'
        views defaultValue: '0'
    }
}
