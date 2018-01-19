package article

class User {

    transient springSecurityService

    String username
    String password
    String firstName
    String lastName
    String email
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    static hasMany = [articles: Article]

    static constraints = {
        username blank: false, unique: true
        password blank: false
    }

    static mapping = {
        articles joinTable: [name: "user_article", key: 'user_id']
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}
