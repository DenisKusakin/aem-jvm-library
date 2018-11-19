package org.deniskusakin.aemlibrary.osgi.impl

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import org.deniskusakin.aemlibrary.ServerSettings
import org.deniskusakin.aemlibrary.osgi.Bundle
import org.deniskusakin.aemlibrary.osgi.BundleState
import org.deniskusakin.aemlibrary.osgi.BundlesInfo
import org.deniskusakin.aemlibrary.osgi.BundlesService

class BundlesServiceImpl(private val serverSettings: ServerSettings) : BundlesService {
    override fun stopBundle(id: Int) {
        val (_, response, result) = Fuel
                .post("${serverSettings.url}/system/console/bundles/$id", listOf(Pair("action", "stop")))
                .timeout(networkTimeout)
                .timeoutRead(networkTimeout)
                .authenticate(serverSettings.login, serverSettings.password)
                .responseString()
        when (result) {
            is Result.Failure -> throw Exception("Failed to stop")
        }
    }

    override fun startBundle(id: Int) {
        val (_, response, result) = Fuel
                .post("${serverSettings.url}/system/console/bundles/$id", listOf(Pair("action", "start")))
                .timeout(networkTimeout)
                .timeoutRead(networkTimeout)
                .authenticate(serverSettings.login, serverSettings.password)
                .responseString()
        when (result) {
            is Result.Failure -> throw Exception("Failed to start")
        }
    }

    private val networkTimeout = 10 * 60 * 1000

    override fun bundles(): BundlesInfo {
        val (_, response, result) = Fuel
                .get("${serverSettings.url}/system/console/bundles.json")
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
                val bundlesResponse = Gson()
                        .fromJson<BundlesInfoResponse>(responseString, BundlesInfoResponse::class.java)
                bundlesResponse.toBundlesInfo(this)
            }
        }
    }

    data class BundlesInfoResponse(val data: List<BundleResponse>)
    data class BundleResponse(val id: Int, val name: String, val version: String, val stateRaw: Int, val symbolicName: String)

    private fun BundlesInfoResponse.toBundlesInfo(service: BundlesService): BundlesInfo {
        return BundlesInfoImpl(bundles = this.data.map { it.toBundle(service) })
    }

    private fun BundleResponse.toBundle(service: BundlesService): Bundle {
        val name = this.name
        val version = this.version
        val symbolicName = this.symbolicName
        val state = when (this.stateRaw) {
            32 -> BundleState.ACTIVE
            2 -> BundleState.INSTALLED
            4 -> BundleState.RESOLVED
            8 -> BundleState.STARTING
            16 -> BundleState.STOPPING
            1 -> BundleState.UNINSTALLED
            else -> BundleState.UNKNOWN
        }
        val id = this.id

        return BundleImpl(
                id = id,
                name = name,
                symbolicName = symbolicName,
                state = state,
                version = version,
                bundlesService = service
        )
    }
}