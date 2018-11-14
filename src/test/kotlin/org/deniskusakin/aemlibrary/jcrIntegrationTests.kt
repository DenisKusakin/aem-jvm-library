package org.deniskusakin.aemlibrary

import org.deniskusakin.aemlibrary.groovyconsole.impl.GroovyConsoleServiceImpl
import org.junit.Ignore
import org.junit.Test

@Ignore
class IntegrationTests {
    private val settings = ServerSettings(
//            url = "http://localhost:8080",
            url = "http://eprupetw1009.petersburg.epam.com:4502",
            webDavPath = "server",
            login = "admin",
            password = "admin",
            workspace = "default")

    @Test
    fun propsTest() {
        val session = openConnection(settings)
        session.node("/content").props.list().forEach { System.out.println(it) }
    }

    @Test
    fun setPropertyTest() {
        val session = openConnection(settings)
        session.node("/content").props["testMultipleProp"] = listOf<Long>(1, 2, 3)
        session.commit()
    }

    @Test
    fun childrenTest() {
        val session = openConnection(settings)
        session.node("/content").children.forEach {
            System.out.println(it.path)
            it.children.forEach { x -> System.out.println("  " + x.name) }
        }
        //session.node("/content").children.map { it.name }.forEach { System.out.println(it) }
    }

    @Test
    fun groovyTest() {
        val gc = GroovyConsoleServiceImpl(settings)
        System.out.println(gc.exec("return 1"))
    }
}