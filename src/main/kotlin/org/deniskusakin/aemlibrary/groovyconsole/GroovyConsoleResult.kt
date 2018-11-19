package org.deniskusakin.aemlibrary.groovyconsole

import com.google.gson.Gson

/**
 * @author Denis_Kusakin. 11/13/2018.
 */
sealed class GroovyConsoleResult {
    fun <T> to(clazz: Class<T>): T {
        return when (this) {
            is GroovyConsoleSuccessResult -> {
                Gson().fromJson<T>(this.result, clazz)
            }
            else -> throw Exception("Failed to deserialize json")
        }
    }
}

data class GroovyConsoleSuccessResult(val result: String, val output: String, val runningTime: String) : GroovyConsoleResult()
data class GroovyConsoleErrorResult(val exceptionStackTrace: String) : GroovyConsoleResult()