package org.deniskusakin.aemlibrary

/**
 * @author Denis_Kusakin. 11/8/2018.
 */
//fun main() {
//    val params = HashMap<String, Any>()
//    params[Spi2davexRepositoryServiceFactory.PARAM_REPOSITORY_URI] = "http://localhost:8080/server"
////    if (batchReadConfig != null) {
////        params[Spi2davexRepositoryServiceFactory.PARAM_BATCHREAD_CONFIG] = batchReadConfig
////    }
//    params[Jcr2spiRepositoryFactory.PARAM_REPOSITORY_SERVICE_FACTORY] = Spi2davexRepositoryServiceFactory::class.java.name
//    params[Jcr2spiRepositoryFactory.PARAM_ITEM_CACHE_SIZE] = 128
//    params[Jcr2spiRepositoryFactory.PARAM_LOG_WRITER_PROVIDER] = Slf4jLogWriterProvider()
//    val repository = RepositoryFactoryImpl().getRepository(params)
//    val session = repository.login(SimpleCredentials("admin", "admin".toCharArray()), "default")
//    val node = session.getNode("/content")
//    println(node.name)
//}

fun main(args: Array<String>) {
    val settings = ServerSettings(
            url = "http://localhost:8080/server",
            login = "admin",
            password = "admin",
            workspace = "default")
    val session = openConnection(settings)
    //session.node("/").children.map { it.name }.forEach { System.out.println(it) }
    session.node("/").props.list().forEach { System.out.println(it) }
    //main()
}