package article

class Tag {
    String name
    static hasMany = [articles: Article]
    static belongsTo = Article
    static constraints = {
        name unique: true
    }
}
