package org.deniskusakin.aemlibrary.osgi.impl

import org.deniskusakin.aemlibrary.osgi.Bundle
import org.deniskusakin.aemlibrary.osgi.BundleState
import org.deniskusakin.aemlibrary.osgi.BundlesService

class BundleImpl(
        private val bundlesService: BundlesService,
        override val id: Int,
        override val name: String,
        override val symbolicName: String,
        override val state: BundleState,
        override val version: String) : Bundle {
    override fun start() {
        bundlesService.startBundle(id)
    }

    override fun stop() {
        bundlesService.stopBundle(id)
    }
}