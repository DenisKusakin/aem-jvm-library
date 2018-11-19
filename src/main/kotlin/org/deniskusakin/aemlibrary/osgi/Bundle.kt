package org.deniskusakin.aemlibrary.osgi

interface Bundle {
    val name: String
    val version: String
    val symbolicName: String
    val state: BundleState
    val id: Int

    fun start()
    fun stop()
}