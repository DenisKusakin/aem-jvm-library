package org.deniskusakin.aemlibrary

import org.apache.commons.io.IOUtils
import org.deniskusakin.aemlibrary.groovyconsole.impl.GroovyConsoleServiceImpl
import org.deniskusakin.aemlibrary.jcr.BinaryPropValue
import org.deniskusakin.aemlibrary.jcr.asString
import org.deniskusakin.aemlibrary.osgi.BundleState
import org.deniskusakin.aemlibrary.osgi.impl.BundlesServiceImpl
import org.deniskusakin.aemlibrary.sling.impl.InfoServiceImpl
import org.junit.Ignore
import org.junit.Test

@Ignore
class IntegrationTests {
    private val settings = ServerSettings(
            url = "http://localhost:4502",
//            url = "http://localhost:8080",
            webDavPath = "crx/server",
//            webDavPath = "server",
            login = "admin",
            password = "admin",
//            workspace = "default"
            workspace = "crx.default"
    )

    @Test
    fun propsTest() {
        val session = openConnection(settings)
        session
                .node("/etc/react-clientlibs/nashorn-polyfill.js/jcr:content")
                .props.list()
                .forEach { System.out.println(it) }
    }

    @Test
    fun setPropertyTest() {
        val session = openConnection(settings)
        session.node("/content").props["testMultipleProp"] = listOf(1L, 2L, 3L)
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
        val script = """
            class Test {
                def a;
                Integer b;
            }
            def test = new Test()
            test.a = "Hello"
            test.b = 1;
            def json = JsonOutput.toJson(test)
            return json
        """.trimIndent()

        //System.out.print(gc.exec(script))
        System.out.print(gc.exec(script).to(TestG::class.java).a)
    }

    @Test
    fun runModesTest() {
        val infoService = InfoServiceImpl(settings)
        System.out.print(infoService.runModes().asStrings())
    }

    data class TestG(val a: String, val b: Int)

    @Test
    fun testBinaryProp() {
        val session = openConnection(settings)
        val prop = session
                .node("/libs/clientlibs/ckeditor/resources/ckeditor/config.js/jcr:content")
                .props["jcr:data"]
        System.out.println(prop.asString())
    }

    @Test
    fun bundlesTest() {
        val bundlesService = BundlesServiceImpl(settings)
        bundlesService.bundles().asCollection()
                //.forEach { it.start() }
//                .filter { it.state != BundleState.ACTIVE }
//                .forEach { it.start() }
                .forEach { System.out.println("${it.id}: ${it.symbolicName} -> ${it.state}") }
    }
}