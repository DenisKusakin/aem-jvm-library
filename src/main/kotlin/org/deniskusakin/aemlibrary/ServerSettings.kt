package org.deniskusakin.aemlibrary

data class ServerSettings(
        val url: String,
        val webDavPath: String,
        val workspace: String,
        val login: String,
        val password: String
)