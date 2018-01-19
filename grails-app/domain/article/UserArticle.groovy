package article

class UserArticle implements Serializable {
    User user
    Article article

    static constraints = {
    }

    static mapping = {
        table 'user_article'
        id composite: ['user', 'article']
        version false
    }
}
