package article

class Tag {
    String name
//    static belongsTo = [articles: Article]
    static constraints = {
        name unique: true
    }
}
