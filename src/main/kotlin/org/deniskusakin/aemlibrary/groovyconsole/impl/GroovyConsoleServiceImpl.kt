package org.deniskusakin.aemlibrary.groovyconsole.impl

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import org.deniskusakin.aemlibrary.ServerSettings
import org.deniskusakin.aemlibrary.groovyconsole.GroovyConsoleErrorResult
import org.deniskusakin.aemlibrary.groovyconsole.GroovyConsoleResult
import org.deniskusakin.aemlibrary.groovyconsole.GroovyConsoleService
import org.deniskusakin.aemlibrary.groovyconsole.GroovyConsoleSuccessResult

class GroovyConsoleServiceImpl(private val serverSettings: ServerSettings) : GroovyConsoleService {
    private val NETWORK_TIMEOUT = 10 * 60 * 1000

    override fun exec(script: String): GroovyConsoleResult {
        val (_, response, result) = Fuel
                .post("${serverSettings.url}/bin/groovyconsole/post.json", listOf(Pair("script", script)))
                .timeout(NETWORK_TIMEOUT)
                .timeoutRead(NETWORK_TIMEOUT)
                .authenticate(serverSettings.login, serverSettings.password)
                .responseString()

        return when (result) {
            is Result.Failure -> {
                throw Exception("")
            }
            is Result.Success -> {
                val output = Gson()
                        .fromJson<GroovyConsoleOutput>(String(response.data), GroovyConsoleOutput::class.java)
                if (output.exceptionStackTrace.isBlank()) {
                    GroovyConsoleSuccessResult(
                            result = output.result,
                            runningTime = output.runningTime,
                            output = output.output)
                } else {
                    GroovyConsoleErrorResult(exceptionStackTrace = output.exceptionStackTrace)
                }
            }
        }
        Fuel.testMode {

        }
    }

    data class GroovyConsoleOutput(
            val output: String,
            val runningTime: String,
            val exceptionStackTrace: String,
            val result: String
    )
}