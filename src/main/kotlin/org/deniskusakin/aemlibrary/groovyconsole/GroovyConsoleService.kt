package org.deniskusakin.aemlibrary.groovyconsole

/**
 * @author Denis_Kusakin. 11/13/2018.
 */
interface GroovyConsoleService {
    fun exec(script: String): GroovyConsoleResult
}