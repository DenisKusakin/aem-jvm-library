package org.deniskusakin.aemlibrary

import org.apache.jackrabbit.client.RepositoryFactoryImpl
import org.apache.jackrabbit.jcr2spi.Jcr2spiRepositoryFactory
import org.apache.jackrabbit.spi.commons.logging.Slf4jLogWriterProvider
import java.util.HashMap
import org.apache.jackrabbit.spi2davex.Spi2davexRepositoryServiceFactory
import javax.jcr.SimpleCredentials

/**
 * @author Denis_Kusakin. 11/8/2018.
 */
fun main() {
    val params = HashMap<String, Any>()
    params[Spi2davexRepositoryServiceFactory.PARAM_REPOSITORY_URI] = "http://admin:admin@localhost:4502/crx/server"
//    if (batchReadConfig != null) {
//        params[Spi2davexRepositoryServiceFactory.PARAM_BATCHREAD_CONFIG] = batchReadConfig
//    }
    params[Jcr2spiRepositoryFactory.PARAM_REPOSITORY_SERVICE_FACTORY] = Spi2davexRepositoryServiceFactory::class.java.name
    params[Jcr2spiRepositoryFactory.PARAM_ITEM_CACHE_SIZE] = 128
    params[Jcr2spiRepositoryFactory.PARAM_LOG_WRITER_PROVIDER] = Slf4jLogWriterProvider()
    val repository = RepositoryFactoryImpl().getRepository(params)
    val session = repository.login(SimpleCredentials("admin", "admin".toCharArray()), "crx.default")
    val node = session.getNode("/content/dam")
    println(node.name)
}

fun main(args: Array<String>) {
    main()
}