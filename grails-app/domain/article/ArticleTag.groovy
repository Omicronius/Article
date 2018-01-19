package article

class ArticleTag implements Serializable {
    Article article
    Tag tag

    static constraints = {
    }

    static mapping = {
        table 'article_tag'
        id composite: ['article', 'tag']
        version false
    }
}
