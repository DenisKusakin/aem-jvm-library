package org.deniskusakin.aemlibrary.groovyconsole

/**
 * @author Denis_Kusakin. 11/13/2018.
 */
sealed class GroovyConsoleResult

data class GroovyConsoleSuccessResult(val result: String, val output: String, val runningTime: String)
data class GroovyConsoleErrorResult(val exceptionStackTrace: String)