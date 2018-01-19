import article.Article
import article.UserRole
import article.User
import article.Tag
import article.Role

class BootStrap {

    def init = { servletContext ->
        def adminRole = Role.findOrSaveWhere(authority: "ROLE_ADMIN")
        def userRole = Role.findOrSaveWhere(authority: "ROLE_USER")

        def tag1 = new Tag(name: 'science')
        def tag2 = new Tag(name: 'history')
        def tag3 = new Tag(name: 'astronomy')

        def art1 = new Article(
                title: 'Scientific article',
                content: 'Increased fluctuations in the path of the North Atlantic jet stream since the 1960s coincide with more extreme weather events in Europe such as heat waves, wildfires and flooding. The new research is the first reconstruction of historical changes in the North Atlantic jet stream prior to the 20th century. By using tree rings, the researchers developed a historical look at the position of the North Atlantic jet back to 1725',
                views: 1556,
                dateCreated: ++new Date(),
                lastUpdated: ++new Date(),
                tags: [tag1, tag2])

        def art2 = new Article(
                title: 'Historical article',
                content: 'Scientists discovered a dinosaur fossil with feathers so well-preserved that they were able to see the feathers\' microscopic color-bearing structures. By comparing the shapes of those feather structures with the structures in modern bird feathers, they\'re able to infer that the new dino, Caihong juji (\'rainbow with the big crest\') had iridescent rainbow feathers like a hummingbird.',
                views: 2546,
                dateCreated: new Date(),
                lastUpdated: new Date(),
                tags: [tag2])

        def art3 = new Article(
                title: 'Astronomical article',
                content: 'In 2014, astronomers found an enormous galaxy cluster contains the mass of a staggering three million billion suns -- so it\'s little wonder that it has earned the nickname of "El Gordo" ("the Fat One" in Spanish)! Known officially as ACT-CLJ0102-4915, it is the largest, hottest, and brightest X-ray galaxy cluster ever discovered in the distant Universe.',
                views: 714,
                dateCreated: --new Date(),
                lastUpdated: --new Date(),
                tags: [tag3])

        def admin = new User(
                username: 'ad',
                password: 'ad',
                firstName: 'ad',
                lastName: 'ad',
                email: 'admin@admin',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false
        ).save()

        def user1 = new User(
                username: 'us',
                password: 'us',
                firstName: 'Konstantin',
                lastName: 'Klimov',
                email: 'user@user',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                articles: [art1, art2, art3]
        ).save()

        def user2 = new User(
                username: 'us2',
                password: 'us2',
                firstName: 'Mahatma',
                lastName: 'Gandhi',
                email: 'user2@user2',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                articles: [art2]
        ).save()

        UserRole.create(admin, adminRole, true)
        UserRole.create(user1, userRole, true)
        UserRole.create(user2, userRole, true)

    }
    def destroy = {
    }
}
