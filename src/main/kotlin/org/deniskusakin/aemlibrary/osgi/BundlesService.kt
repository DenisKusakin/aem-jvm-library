package org.deniskusakin.aemlibrary.osgi

interface BundlesService {
    fun bundles(): BundlesInfo
    fun stopBundle(id: Int)
    fun startBundle(id: Int)
}