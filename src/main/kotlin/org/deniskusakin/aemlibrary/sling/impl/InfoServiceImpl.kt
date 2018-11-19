package org.deniskusakin.aemlibrary.sling.impl

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import org.deniskusakin.aemlibrary.ServerSettings
import org.deniskusakin.aemlibrary.sling.InfoService
import org.deniskusakin.aemlibrary.sling.SlingRunModes

class InfoServiceImpl(private val serverSettings: ServerSettings) : InfoService {
    private val networkTimeout = 10 * 60 * 1000

    override fun runModes(): SlingRunModes {
        val runModesPattern = """.*Run Modes = \[(.*)]".*"""

        val (_, response, result) = Fuel
                .get("${serverSettings.url}/system/console/status-slingsettings.json")
                .timeout(networkTimeout)
                .timeoutRead(networkTimeout)
                .authenticate(serverSettings.login, serverSettings.password)
                .responseString()

        return when (result) {
            is Result.Failure -> {
                throw Exception("")
            }
            is Result.Success -> {
                val responseString = String(response.data)
                val regex = runModesPattern.toRegex()
                val matchResult = regex.find(responseString)
                val runModesString = matchResult!!.groups[1]!!.value
                val runModesList = runModesString.split(",").map { it.trim() }
                SlingRunModesImpl(runModesList)
            }
        }
    }

}