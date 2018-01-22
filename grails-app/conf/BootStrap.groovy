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
        def tag4 = new Tag(name: 'biology')

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
                views: 124,
                dateCreated: --new Date(),
                lastUpdated: --new Date(),
                tags: [tag3])

        def art4 = new Article(
                title: 'Biological article',
                content: 'Acoustic tractor beams use the power of sound to hold particles in mid-air, and unlike magnetic levitation, they can grab most solids or liquids even small insects. For the first time engineers have shown it is possible to stably trap objects larger than the wavelength of sound in an acoustic tractor beam. This discovery could enable the manipulation of drug capsules or micro-surgical implements within the body. The discovery could even lead to levitating humans.',
                views: 1156,
                dateCreated: --new Date(),
                lastUpdated: --new Date(),
                tags: [tag4])

        def art5 = new Article(
                title: 'Zoology article',
                content: 'The afterglow from the distant neutron-star merger detected last August has continued to brighten - much to the surprise of astrophysicists studying the aftermath of the massive collision that took place about 138 million light years away and sent gravitational waves rippling through the universe. New observations indicate that the gamma ray burst unleashed by the collision is more complex than scientists initially imagined.',
                views: 2145,
                dateCreated: --new Date(),
                lastUpdated: --new Date(),
                tags: [tag4])

        def art6 = new Article(
                title: 'Psychology article',
                content: 'In a scientific first, researchers have turned skin cells from mice into stem cells by activating a specific gene in the cells using CRISPR technology. The innovative approach offers a potentially simpler technique to produce the valuable cell type and provides important insights into the cellular reprogramming process.',
                views: 587,
                dateCreated: --new Date(),
                lastUpdated: --new Date(),
                tags: [tag3, tag4])

        def art7 = new Article(
                title: 'Health article',
                content: 'It is easier to spread the influenza virus (flu) than previously thought, according to a new study. People commonly believe that they can catch the flu by exposure to droplets from an infected person\'s coughs or sneezes or by touching contaminated surfaces. But, new information about flu transmission reveals that we may pass the flu to others just by breathing.',
                views: 982,
                dateCreated: --new Date(),
                lastUpdated: --new Date(),
                tags: [tag1, tag3])

        def admin = new User(
                username: 'a',
                password: 'a',
                firstName: 'ad',
                lastName: 'ad',
                email: 'admin@admin',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false
        ).save()

        def user1 = new User(
                username: 'u',
                password: 'u',
                firstName: 'Konstantin',
                lastName: 'Klimov',
                email: 'user@user',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                articles: [art1, art2, art3, art5, art7]
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
                articles: [art2, art4, art6, art7]
        ).save()

        UserRole.create(admin, adminRole, true)
        UserRole.create(user1, userRole, true)
        UserRole.create(user2, userRole, true)

    }
    def destroy = {
    }
}
