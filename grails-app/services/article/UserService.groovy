package article

import grails.transaction.Transactional

@Transactional
class UserService {

    def getUsersByArticle(Article article) {
        User.executeQuery('select u from User u where :article in elements(u.articles)', [article: article])
    }

    def findUsersBySearchWord(String searchWord) {
        User.createCriteria().list{
            like("firstName", "%${searchWord}%")
        }
    }
}
