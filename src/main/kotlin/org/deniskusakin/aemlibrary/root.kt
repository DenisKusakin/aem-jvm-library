package org.deniskusakin.aemlibrary

import org.apache.jackrabbit.client.RepositoryFactoryImpl
import org.apache.jackrabbit.jcr2spi.Jcr2spiRepositoryFactory
import org.apache.jackrabbit.spi.commons.logging.Slf4jLogWriterProvider
import org.apache.jackrabbit.spi2davex.Spi2davexRepositoryServiceFactory
import java.util.*
import javax.jcr.SimpleCredentials

fun openConnection(settings: ServerSettings): AemSession {
    val params = HashMap<String, Any>()
    params[Spi2davexRepositoryServiceFactory.PARAM_REPOSITORY_URI] = "${settings.url}/${settings.webDavPath}"
    params[Jcr2spiRepositoryFactory.PARAM_REPOSITORY_SERVICE_FACTORY] =
            Spi2davexRepositoryServiceFactory::class.java.name
    params[Jcr2spiRepositoryFactory.PARAM_ITEM_CACHE_SIZE] = 128
    params[Jcr2spiRepositoryFactory.PARAM_LOG_WRITER_PROVIDER] = Slf4jLogWriterProvider()
    val repository = RepositoryFactoryImpl().getRepository(params)
    val credentials = SimpleCredentials(settings.login, settings.password.toCharArray())
    val session = repository.login(credentials, settings.workspace)

    return AemSessionImpl(session)
}