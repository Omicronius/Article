package article

class Article {
    String title
    String content
    Long views = 0
    Date dateCreated
    Date lastUpdated
    static hasMany = [contributors: User, tags: Tag]
    static belongsTo = User

    static constraints = {
    }

    static mapping = {
        content type: 'text'
        views defaultValue: '0'
    }
}
